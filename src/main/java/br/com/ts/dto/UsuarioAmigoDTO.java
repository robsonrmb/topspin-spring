package br.com.ts.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioAmigoDTO {

	private long id;
	@NotBlank
	private long idUsuario;
	@NotBlank
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
