package br.com.tayenecarla.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.tayenecarla.model.UsuarioModel;
import br.com.tayenecarla.repository.UsuarioRepository;
import br.com.tayenecarla.repository.entity.UsuarioEntity;
import br.com.tayenecarla.uteis.Uteis;


@Named(value="usuarioLoginController")
@SessionScoped
public class UsuarioLoginController implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private UsuarioEntity usuarioEntity;
	
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	public UsuarioModel GetUsuarioSession(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
	
	public String Logout(){
						
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index?faces-redirect=true";
	}
	public String EfetuarLogin(){
			
		if(StringUtils.isEmpty(usuarioModel.getEmail()) || StringUtils.isBlank(usuarioModel.getEmail())){
			
			Uteis.Mensagem("Insira seu login:");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			
			Uteis.Mensagem("Insira sua senha:");
			return null;
		}
		else{	

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
			
			if(usuarioEntity!= null){
							
				usuarioModel.setSenha(null);
				usuarioModel.setEmail(usuarioEntity.getEmail());
				
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				
				
				return "sistema/cadastrarPessoa?faces-redirect=true";
			}
			else{
				
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
		
		
	}
	
}