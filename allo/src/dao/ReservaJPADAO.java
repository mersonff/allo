package dao;

import modelo.Reserva;


public class ReservaJPADAO extends GenericJPADAO<Reserva> implements ReservaDAO{
	
	public ReservaJPADAO(){
		this.persistentClass = Reserva.class;
	}
	
}	
