package br.com.ts.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@SuppressWarnings("serial")
@Entity
@Table(name="AREA_AVALIACAO")
public class AreaAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	// técnica | tática ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy="areaAvaliacao")
	private List<TipoAvaliacao> tipo;
	
	public AreaAvaliacao() {}
	
	public AreaAvaliacao(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoAvaliacao> getTipo() {
		return tipo;
	}

	public void setTipo(List<TipoAvaliacao> tipo) {
		this.tipo = tipo;
	}
	
}
