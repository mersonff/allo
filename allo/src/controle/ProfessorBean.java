package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import modelo.Professor;
import dao.ProfessorDAO;
import dao.ProfessorJPADAO;

@ManagedBean
public class ProfessorBean extends AbstractBean {

	private Professor professor;
	private List<Professor> professores;
	private List<Professor> filteredProfessores;

	public ProfessorBean() {
		this.setProfessores(new ArrayList<Professor>());
		this.setProfessor(new Professor());
		pesquisarTodos();
	}

	public void cadastrar() {
		ProfessorDAO operDAO = new ProfessorJPADAO();
		Professor p = operDAO.find(this.professor.getLogin());
		if (p == null) {
			operDAO.save(this.professor);
			displayInfoMessageToUser("Cadastrado com sucesso!");
			this.professor = new Professor();
		} else {
			displayErrorMessageToUser("Esse Login já é utilizado.");
		}

	}

	public void pesquisarTodos() {
		ProfessorDAO operDAO = new ProfessorJPADAO();
		this.professores = operDAO.find();
	}

	public void excluir() {
		ProfessorDAO operDAO = new ProfessorJPADAO();
		operDAO.delete(this.professor);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.professores = operDAO.find();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Professor> getFilteredProfessores() {
		return filteredProfessores;
	}

	public void setFilteredProfessores(List<Professor> filteredProfessores) {
		this.filteredProfessores = filteredProfessores;
	}

}
