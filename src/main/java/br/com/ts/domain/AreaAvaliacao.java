package br.com.ts.domain;

import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="AREA_AVALIACAO")
public class AreaAvaliacao extends AbstractEntity<Long> {

	// técnica | tática ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy="areaAvaliacao")
	private List<TipoAvaliacao> tipo;
	
	public AreaAvaliacao() {}
	
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
