package br.com.ts.dto;

import java.io.Serializable;

public class CadastroLoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String senha;
	private String estado;
	private String sexo;
	
	public CadastroLoginDTO() {
	}
	
	public CadastroLoginDTO(String nome, String email, String senha, String estado, String sexo) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.estado = estado;
		this.sexo = sexo;
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
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
