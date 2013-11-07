package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import modelo.Reserva;
import dao.ReservaDAO;
import dao.ReservaJPADAO;

@ManagedBean
public class ReservaBean extends AbstractBean{
	
	private Reserva reserva;
	private List<Reserva> reservaes;
	
	public ReservaBean(){
		this.setReservaes(new ArrayList<Reserva>());
		this.setReserva(new Reserva());
		pesquisarTodos();
	}
	
	public void cadastrar(){
		ReservaDAO operDAO = new ReservaJPADAO();
		operDAO.save(this.reserva);
		displayInfoMessageToUser("Cadastrado com sucesso!");
	}
	
	public void pesquisarTodos(){
		ReservaDAO operDAO = new ReservaJPADAO();
		this.reservaes = operDAO.find();
	}
	
	public void excluir(Reserva oper){
		ReservaDAO operDAO = new ReservaJPADAO();
		operDAO.delete(oper);
		displayInfoMessageToUser("Excluido com sucesso!");
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getReservaes() {
		return reservaes;
	}

	public void setReservaes(List<Reserva> reservaes) {
		this.reservaes = reservaes;
	}
	

}
