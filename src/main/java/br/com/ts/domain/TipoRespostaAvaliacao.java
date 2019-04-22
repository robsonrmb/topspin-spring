package br.com.ts.domain;

import java.io.Serializable;

import javax.persistence.*;

//@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_RESPOSTA_AVALIACAO")
public class TipoRespostaAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	// ruim | regular | bom | ótimo ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	public TipoRespostaAvaliacao() {}

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
	
}
