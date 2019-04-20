package br.com.ts.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="ACESSO")
public class Acesso extends AbstractEntity<Long> {

	@Column(name = "email", nullable = false, unique = true, length = 60)
	private String email;
	
	@Column(name = "senha", nullable = false, length = 10)
	private String senha;
	
	public Acesso() {}
	
	public Acesso(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
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
