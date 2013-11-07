package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReserva;

	@ManyToOne
	@JoinColumn(name = "idRecurso")
	private Recurso recurso;

	@Temporal(TemporalType.DATE)
	private Date data;

	private boolean aula1;
	private boolean aula2;
	private boolean aula3;
	private boolean aula4;
	private boolean aula5;
	
	public Reserva(){
		
	}

	public long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAula1() {
		return aula1;
	}

	public void setAula1(boolean aula1) {
		this.aula1 = aula1;
	}

	public boolean isAula2() {
		return aula2;
	}

	public void setAula2(boolean aula2) {
		this.aula2 = aula2;
	}

	public boolean isAula3() {
		return aula3;
	}

	public void setAula3(boolean aula3) {
		this.aula3 = aula3;
	}

	public boolean isAula4() {
		return aula4;
	}

	public void setAula4(boolean aula4) {
		this.aula4 = aula4;
	}

	public boolean isAula5() {
		return aula5;
	}

	public void setAula5(boolean aula5) {
		this.aula5 = aula5;
	}

}
