package br.com.tayenecarla.usuario.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tayenecarla.model.UsuarioModel;
import br.com.tayenecarla.repository.UsuarioRepository;
import br.com.tayenecarla.uteis.Uteis;

@Named(value="consultarUsuarioController")
@ViewScoped
public class ConsultarUsuarioController implements Serializable {

		
	private static final long serialVersionUID = 1L;
	

	@Inject 
	private UsuarioModel usuarioModel;

	@Produces 
	private List<UsuarioModel> usuarios;
	
	@Inject transient
	private UsuarioRepository usuarioRepository;
		
	@Inject
	UsuarioLoginController usuarioController;

	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}		
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	
	@PostConstruct
	public void init(){
		
		this.usuarios = usuarioRepository.GetUsuarios();
	}
	
	public void CadastrarUsuario(){
		
		usuarioModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
				
		usuarioRepository.SalvarNovoRegistro(this.usuarioModel);
		
		this.usuarioModel = null;
		
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		
	}
		
	public void Editar(UsuarioModel usuarioModel){
		
		usuarioModel.setNome(usuarioModel.getNome());
		this.usuarioModel = usuarioModel;
		Uteis.MensagemInfo("Registro alterado com sucesso");		
	}
	
 	public void AlterarRegistro(){
		
		this.usuarioRepository.AlterarRegistro(this.usuarioModel);	
		this.init();
		Uteis.MensagemInfo("Usuário alterado com sucesso!");
	}
	
	
	public void ExcluirUsuario(UsuarioModel usuarioModel){
		
		this.usuarioRepository.ExcluirRegistro(usuarioModel.getUser_id());
		this.usuarios.remove(usuarioModel);
		Uteis.MensagemInfo("Usuário deletado com sucesso!");
		
	}
		
}