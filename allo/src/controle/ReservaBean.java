package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Professor;
import modelo.Reserva;
import dao.ProfessorDAO;
import dao.ProfessorJPADAO;
import dao.ReservaDAO;
import dao.ReservaJPADAO;

@ManagedBean
public class ReservaBean extends AbstractBean {

	private Reserva reserva;
	private List<Reserva> reservas;
	private List<Reserva> reservasPorRecursos;
	private List<Reserva> reservasFiltradas;
	private List<Reserva> reservasHoje;
	private List<Reserva> reservasPorProfessor;

	public ReservaBean() {
		this.setReservas(new ArrayList<Reserva>());
		this.setReserva(new Reserva());
		pesquisarTodos();
	}

	public void cadastrar() {
		ReservaDAO reDAO = new ReservaJPADAO();
		ProfessorDAO pDAO = new ProfessorJPADAO();
		Professor p = pDAO.find(this.reserva.getProfessor().getLogin());
		boolean verifica = verificaReserva(this.reserva);
		if (p != null && verifica == true) {
			reDAO.save(this.reserva);
			displayInfoMessageToUser("Cadastrado com sucesso!");
			System.out.println(reservasPorRecursos);
			this.reserva = new Reserva();
		} else {
			if (verifica == false) {
				displayErrorMessageToUser("Essa recurso já foi alocado por outro professor para essa(s) aula(s).");
			} else {
				displayErrorMessageToUser("Login Inexistente!");
			}
		}

	}

	public void pesquisarTodos() {
		ReservaDAO reDAO = new ReservaJPADAO();
		this.reservas = reDAO.find();
	}

	public void excluir() {
		ReservaDAO reDAO = new ReservaJPADAO();
		reDAO.delete(this.reserva);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.reservas = reDAO.find();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Reserva> getReservasPorRecursos() {
		List<Reserva> temp = new ArrayList<Reserva>();
		for (Reserva r : reservas) {
			if (r.getRecurso().getCodigo()
					.equals(this.reserva.getRecurso().getCodigo())) {
				temp.add(r);
			}
		}
		reservasPorRecursos = temp;
		return reservasPorRecursos;
	}

	public void setReservasPorRecursos(List<Reserva> reservasPorRecursos) {
		this.reservasPorRecursos = reservasPorRecursos;
	}

	public boolean verificaReserva(Reserva r) {
		boolean bool = true;
		for (Reserva re : reservas) {
			if (re.getData().equals(r.getData())
					&& re.getRecurso().getCodigo()
							.equals(r.getRecurso().getCodigo())
					&& re.getAulas().toString().equals(r.getAulas().toString())) {
				bool = false;
			} else if (re.getData().equals(r.getData())
					&& re.getRecurso().getCodigo()
							.equals(r.getRecurso().getCodigo())) {
				for (String s : r.getAulas()) {
					if (re.getAulas().contains(s)) {
						bool = false;
					}
				}
				for (String s : re.getAulas()) {
					if (r.getAulas().contains(s)) {
						bool = false;
					}
				}

			}
		}
		return bool;
	}

	public List<Reserva> getReservasFiltradas() {
		return reservasFiltradas;
	}

	public void setReservasFiltradas(List<Reserva> reservasFiltradas) {
		this.reservasFiltradas = reservasFiltradas;
	}

	public List<Reserva> getReservasHoje() {
		List<Reserva> temp = new ArrayList<Reserva>();
		for (Reserva r : reservas) {
			if (r.isHoje() == true) {
				temp.add(r);
			}
		}
		reservasHoje = temp;
		return reservasHoje;
	}

	public void setReservasHoje(List<Reserva> reservasHoje) {
		this.reservasHoje = reservasHoje;
	}

	public List<Reserva> getReservasPorProfessor() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Professor prof = new Professor();
		ProfessorDAO pDAO = new ProfessorJPADAO();
		prof = pDAO.find(sessao.getAttribute("login"));
		List<Reserva> temp = new ArrayList<Reserva>();
		for (Reserva r : reservas) {
			if (r.getProfessor().getLogin() == prof.getLogin()) {
				temp.add(r);
			}
		}
		reservasPorProfessor = temp;
		return reservasPorProfessor;
	}

	public void setReservasPorProfessor(List<Reserva> reservasPorProfessor) {
		this.reservasPorProfessor = reservasPorProfessor;
	}
}
