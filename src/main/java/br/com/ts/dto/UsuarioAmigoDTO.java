package br.com.ts.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UsuarioAmigoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	@NotNull
	private long idUsuario;
	@NotNull
	private long idAmigo;
	
	public UsuarioAmigoDTO() {}
	
	public UsuarioAmigoDTO(long id, long idUsuario, long idAmigo) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idAmigo = idAmigo;
	}
	
	public UsuarioAmigoDTO(long idUsuario, long idAmigo) {
		super();
		this.idUsuario = idUsuario;
		this.idAmigo = idAmigo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdAmigo() {
		return idAmigo;
	}
	public void setIdAmigo(long idAmigo) {
		this.idAmigo = idAmigo;
	}
	
}
