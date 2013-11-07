package dao;

import modelo.Recurso;


public class RecursoJPADAO extends GenericJPADAO<Recurso> implements RecursoDAO{
	
	public RecursoJPADAO(){
		this.persistentClass = Recurso.class;
	}
	
}	
