package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Administrador;
import dao.AdministradorDAO;
import dao.AdministradorJPADAO;

@ManagedBean
public class AdministradorBean extends AbstractBean {

	private Administrador administrador;
	private List<Administrador> admins;
	private Administrador ativo;
	private String senhaAtual;
	private String novaSenha;
	private String confirmaNovaSenha;

	public AdministradorBean() {
		this.administrador = new Administrador();
		this.setAdmins(new ArrayList<Administrador>());
		findAll();
	}

	public void atualizar() {
		AdministradorDAO adminDAO = new AdministradorJPADAO();
		adminDAO.save(this.ativo);
		displayInfoMessageToUser("Atualizado com sucesso!");
	}

	public void alterarSenha() {
		AdministradorDAO adminDAO = new AdministradorJPADAO();
		if(this.ativo.getSenha().equals(this.senhaAtual)){
			if(this.novaSenha.equals(this.confirmaNovaSenha)){
				this.ativo.setSenha(this.novaSenha);
				adminDAO.save(this.ativo);
				displayInfoMessageToUser("Atualizado com Sucesso!");
			}else{
				displayErrorMessageToUser("Erro na confirmação da senha!");
			}
		}else{
			displayErrorMessageToUser("Senha atual inválida!");
		}
	}

	public void findAll() {
		AdministradorDAO operDAO = new AdministradorJPADAO();
		this.setAdmins(operDAO.find());
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public Administrador getAtivo() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Administrador temp = new Administrador();
		AdministradorDAO adminDAO = new AdministradorJPADAO();
		temp = adminDAO.find(sessao.getAttribute("login"));
		this.ativo = temp;
		return ativo;
	}

	public void setAtivo(Administrador ativo) {
		this.ativo = ativo;
	}

	public List<Administrador> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Administrador> admins) {
		this.admins = admins;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}
	
	

}
