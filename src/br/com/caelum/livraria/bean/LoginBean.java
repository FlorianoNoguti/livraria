package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
	    System.out.println("Fazendo login do usu�rio "
	            + this.usuario.getEmail());
	    
	    /*verifica se o usu�rio est� cadastrado, se estiver seta os valores dentro
	    de usuarioLogado que vai ser recuperado na classe Autorizador*/
	    
	    FacesContext context = FacesContext.getCurrentInstance();
	    boolean existe = new UsuarioDao().existe(this.usuario);

	    
	    if (existe) {
	    	context.getExternalContext().getSessionMap()
	    			.put("usuarioLogado",this.usuario);
	    	
	        return "livro?faces-redirect=true";
	    }
	    
	    context.getExternalContext().getFlash().setKeepMessages(true);
	    context.addMessage(null, new FacesMessage("Usu�rio n�o encontrado"));

	    return "login?faces-redirect=true";
	}
	
	
	/*public String deslogar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
	    boolean existe = new UsuarioDao().existe(this.usuario);
		return "login?faces-redirect=true";
	}*/

}

	

