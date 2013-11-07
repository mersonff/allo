package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import modelo.Recurso;
import dao.RecursoDAO;
import dao.RecursoJPADAO;

@ManagedBean
public class RecursoBean extends AbstractBean{
	
	private Recurso recurso;
	private List<Recurso> recursos;
	private List<Recurso> filteredRecursos;

	
	public RecursoBean(){
		this.setRecursos(new ArrayList<Recurso>());
		this.setRecurso(new Recurso());
		pesquisarTodos();
	}
	
	public void cadastrar(){
		RecursoDAO operDAO = new RecursoJPADAO();
		operDAO.save(this.recurso);
		displayInfoMessageToUser("Cadastrado com sucesso!");
	}
	
	public void pesquisarTodos(){
		RecursoDAO operDAO = new RecursoJPADAO();
		this.recursos = operDAO.find();
	}
	
	public void excluir(Recurso oper){
		RecursoDAO operDAO = new RecursoJPADAO();
		operDAO.delete(oper);
		displayInfoMessageToUser("Excluido com sucesso!");
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
