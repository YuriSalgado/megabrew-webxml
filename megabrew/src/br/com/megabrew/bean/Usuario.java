package br.com.megabrew.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Usuario implements Bean {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private String telefone;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		if (nome != null)
			return nome;
		return "";
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		if (login != null)
			return login;
		return "";
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		if (senha != null)
			return senha;
		return "";
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}