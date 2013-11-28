package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Administrador;
import modelo.Professor;

import dao.AdministradorDAO;
import dao.AdministradorJPADAO;
import dao.ProfessorDAO;
import dao.ProfessorJPADAO;

import util.TipoDeUsuario;
import util.Usuario;

@ManagedBean
public class LoginBean extends AbstractBean {

	private Usuario usuario = new Usuario();
	private TipoDeUsuario tipoDeUsuario = new TipoDeUsuario();

	public LoginBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	public String logar() {
		try {
			if (this.usuario.getTipo().equals(this.tipoDeUsuario.getAdministrador())) {
				AdministradorDAO adminDAO = new AdministradorJPADAO();
				Administrador a = adminDAO.find(this.usuario.getLogin());
				if (a != null && a.getSenha().equals(this.usuario.getSenha())) {
					HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					sessao.setAttribute("login", this.usuario.getLogin());
					sessao.setAttribute("tipo", this.usuario.getTipo());
					return "/pages/admin/home-admin.xhtml?faces-redirect=true";
				} else {
					throw new Exception();
					/*
					 * Mensagem msg = new
					 * Mensagem("Usu√°rio ou senha inv√°lidos!", false, true);
					 * ELFlash.getFlash().put("msg", msg);
					 */
				}
			}

			else if (this.usuario.getTipo().equals(this.tipoDeUsuario.getProfessor())) {
				ProfessorDAO operDAO = new ProfessorJPADAO();
				Professor oper = operDAO.find(this.usuario.getLogin());
				if (oper != null
						&& oper.getSenha().equals(this.usuario.getSenha())) {
					HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					sessao.setAttribute("login", this.usuario.getLogin());
					sessao.setAttribute("tipo", this.usuario.getTipo());
					return "/pages/professor/home-professor.xhtml?faces-redirect=true";
				} else {
					throw new Exception();
				}
			}

		} catch (Exception e) {
			displayErrorMessageToUser("Usu·rio ou senha inv·lidos!");
			return "/index.xhtml";
		}

		return "/index.xhtml";

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

}
