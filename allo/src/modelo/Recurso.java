package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRecurso;
	private String descricao;

	@OneToMany(mappedBy = "recurso")
	private List<Reserva> reservas;

	public Recurso() {
		this.reservas = new ArrayList<Reserva>();
	}

	public long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(long idRecurso) {
		this.idRecurso = idRecurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

}
