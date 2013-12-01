package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recurso {

	@Id
	private String codigo;
	private String descricao;

	@OneToMany(mappedBy = "recurso")
	private List<Reserva> reservas;

	public Recurso() {
		this.reservas = new ArrayList<Reserva>();
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}
