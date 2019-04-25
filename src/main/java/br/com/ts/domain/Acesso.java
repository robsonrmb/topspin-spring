package br.com.ts.domain;

import java.io.Serializable;

import javax.persistence.*;

//@SuppressWarnings("serial")
@Entity
@Table(name="ACESSO")
public class Acesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true, length = 60)
	private String email;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	public Acesso() {}
	
	public Acesso(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
