package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import modelo.Recurso;
import dao.RecursoDAO;
import dao.RecursoJPADAO;

@ManagedBean
public class RecursoBean extends AbstractBean {

	private Recurso recurso;
	private List<Recurso> recursos;
	private List<Recurso> filteredRecursos;

	public RecursoBean() {
		this.setRecursos(new ArrayList<Recurso>());
		this.setRecurso(new Recurso());
		pesquisarTodos();
	}

	public void cadastrar() {
		RecursoDAO recDAO = new RecursoJPADAO();
		Recurso r = recDAO.find(this.recurso.getCodigo());
		if (r == null) {
			recDAO.save(this.recurso);
			displayInfoMessageToUser("Cadastrado com sucesso!");
			this.recurso = new Recurso();
		} else {
			displayErrorMessageToUser("Já existe um recurso cadastrado com esse código.");
		}
	}

	public void pesquisarTodos() {
		RecursoDAO recDAO = new RecursoJPADAO();
		this.recursos = recDAO.find();
	}

	public void excluir() {
		RecursoDAO recDAO = new RecursoJPADAO();
		recDAO.delete(this.recurso);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.recursos = recDAO.find();
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursoes) {
		this.recursos = recursoes;
	}

	public List<Recurso> getFilteredRecursos() {
		return filteredRecursos;
	}

	public void setFilteredRecursos(List<Recurso> filteredRecursos) {
		this.filteredRecursos = filteredRecursos;
	}

}
