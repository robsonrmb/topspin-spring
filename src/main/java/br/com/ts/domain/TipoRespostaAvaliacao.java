package br.com.ts.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_RESPOSTA_AVALIACAO")
public class TipoRespostaAvaliacao extends AbstractEntity<Long> {

	// ruim | regular | bom | ótimo ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	public TipoRespostaAvaliacao() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
