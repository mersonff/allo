package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReserva;

	@Temporal(TemporalType.DATE)
	private Date data;

	@ElementCollection
	private List<String> aulas;

	@ManyToOne
	@JoinColumn(name = "login")
	private Professor professor;

	@OneToOne
	@JoinColumn(name = "codigo")
	private Recurso recurso;

	private boolean hoje;

	public Reserva() {
		this.professor = new Professor();
		this.recurso = new Recurso();
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<String> getAulas() {
		return aulas;
	}

	public void setAulas(List<String> aulas) {
		this.aulas = aulas;
	}

	public boolean isHoje() {
		Calendar dataReserva = new GregorianCalendar();
		dataReserva.setTime(data);
		Calendar datahoje = Calendar.getInstance();
		if (dataReserva.get(Calendar.DATE) == datahoje.get(Calendar.DATE)
				&& dataReserva.get(Calendar.MONTH) == datahoje
						.get(Calendar.MONTH)
				&& dataReserva.get(Calendar.YEAR) == datahoje
						.get(Calendar.YEAR)) {
			hoje = true;
		} else {
			hoje = false;
		}
		return hoje;

	}

	public void setHoje(boolean hoje) {
		this.hoje = hoje;
	}
	
	

}
