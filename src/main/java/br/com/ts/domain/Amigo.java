package br.com.ts.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name =  "busca.porUsuarioEAmigo", 
				query = "SELECT a FROM Amigo a where a.usuario.id = :idUsuario and a.amigo.id = :idAmigo" ),
	@NamedQuery(name =  "lista.porUsuario", 
				query = "SELECT a FROM Amigo a where a.usuario.id = :id" )
})
@Table(name="AMIGO")
public class Amigo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario; 
	
	@ManyToOne
	@JoinColumn(name="id_amigo")
	private Usuario amigo;
	
	public Amigo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getAmigo() {
		return amigo;
	}

	public void setAmigo(Usuario amigo) {
		this.amigo = amigo;
	}
	
}
