package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
public class LogoutBean {
	
	public String sair(){
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    if(sessao != null){
	    	sessao.invalidate();
	    }
		return "/index.xhtml?faces-redirect=true";
	}

}
