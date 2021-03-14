package br.com.tayenecarla.repository;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.MaskFormatter;

import br.com.tayenecarla.model.UsuarioModel;
import br.com.tayenecarla.repository.entity.UsuarioEntity;
import br.com.tayenecarla.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel) {

		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			query.setParameter("email", usuarioModel.getEmail());
			query.setParameter("senha", usuarioModel.getSenha());

			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			return null;
		}

	}

	@Inject
	UsuarioEntity usuarioEntity;

	EntityManager entityManager;

	public void SalvarNovoRegistro(UsuarioModel usuarioModel) {

		entityManager = Uteis.JpaEntityManager();

		usuarioEntity = new UsuarioEntity();
		usuarioEntity.setEmail(usuarioModel.getEmail());
		usuarioEntity.setNome(usuarioModel.getNome());
		usuarioEntity.setTelefone(usuarioModel.getTelefone());
		usuarioEntity.setCelular(usuarioModel.getCelular());
		usuarioEntity.setSenha(usuarioModel.getSenha());
		
		entityManager.persist(usuarioEntity);

	}

	
	public List<UsuarioModel> GetUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("UsuarioEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<UsuarioEntity> usuariosEntity = (Collection<UsuarioEntity>) query.getResultList();

		UsuarioModel usuarioModel = null;

		for (UsuarioEntity usuarioEntity : usuariosEntity) {

			usuarioModel = new UsuarioModel();
			usuarioModel.setUser_id(usuarioEntity.getUser_id());
			usuarioModel.setTelefone(usuarioEntity.getTelefone());
			usuarioModel.setCelular(usuarioEntity.getCelular());
			usuarioModel.setEmail(usuarioEntity.getEmail());
			usuarioModel.setNome(usuarioEntity.getNome());


			UsuarioModel usuarioModel1 = new UsuarioModel();
			usuarioModel1.setEmail(usuarioEntity.getEmail());

			usuariosModel.add(usuarioModel);
		}

		return usuariosModel;

	}

	private UsuarioEntity GetUsuario(int user_id) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(UsuarioEntity.class, user_id);
	}

	public void AlterarRegistro(UsuarioModel usuarioModel) {

		entityManager = Uteis.JpaEntityManager();

		UsuarioEntity usuarioEntity = this.GetUsuario(usuarioModel.getUser_id());

		usuarioEntity.setEmail(usuarioModel.getEmail());
		usuarioEntity.setNome(usuarioModel.getNome());
		usuarioEntity.setTelefone(usuarioModel.getTelefone());
		usuarioEntity.setCelular(usuarioModel.getCelular());

		entityManager.merge(usuarioEntity);
	}

	public void ExcluirRegistro(int user_id) {

		entityManager = Uteis.JpaEntityManager();

		UsuarioEntity usuarioEntity = this.GetUsuario(user_id);

		entityManager.remove(usuarioEntity);
	}

}
