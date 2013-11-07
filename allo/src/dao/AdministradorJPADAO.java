package dao;

import modelo.Administrador;

public class AdministradorJPADAO extends GenericJPADAO<Administrador> implements AdministradorDAO {
	
	public AdministradorJPADAO(){
		this.persistentClass = Administrador.class;
	}

}
