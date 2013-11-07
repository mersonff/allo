package dao;

import modelo.Professor;

public class ProfessorJPADAO extends GenericJPADAO<Professor> implements ProfessorDAO{
	
	public ProfessorJPADAO(){
		this.persistentClass = Professor.class;
	}
	
}	
