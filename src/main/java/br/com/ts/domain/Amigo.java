package br.com.ts.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name =  "busca.porUsuarioEAmigo", 
				query = "SELECT a FROM Amigo a where a.usuario.id = :idUsuario and a.amigo.id = :idAmigo" ),
	@NamedQuery(name =  "lista.porUsuario", 
				query = "SELECT a FROM Amigo a where a.usuario.id = :id" )
})
@Table(name="AMIGO")
public class Amigo extends AbstractEntity<Long> {

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario; 
	
	@ManyToOne
	@JoinColumn(name="id_amigo")
	private Usuario amigo;
	
	public Amigo() {}

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
