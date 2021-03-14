package br.com.tayenecarla.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="tb_usuario")

@NamedQueries({
	
	@NamedQuery(name = "UsuarioEntity.findAll",query= "SELECT p FROM UsuarioEntity p"),
	@NamedQuery(name="UsuarioEntity.findUser",query= "SELECT u FROM UsuarioEntity u WHERE u.email = :email AND u.senha = :senha")
	
})

public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int user_id;
	
	
	@Column(name="ds_nome")
	private String nome;
	
	@Column(name="ds_login")
	private String email;
	
	@Column(name="ds_fone")
	private String telefone;
	
	@Column(name="ds_senha")
	private String senha;
	
	@Column(name="ds_ddd")
	private String ddd;
	
	@Column(name="ds_celular")
	private String celular;

	
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
