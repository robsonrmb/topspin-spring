package br.com.ts.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_RESPOSTA_ESTATISTICA")
public class TipoRespostaEstatistica extends AbstractEntity<Long> {

	// ruim | regular | bom | ótimo ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	public TipoRespostaEstatistica() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/* O mappedBy informa que o mapeamento já foi feito do outro lado.
	@ManyToMany(mappedBy="tipoRespostas")
	private Set<TipoEstatistica> tipoEstatisticas = new HashSet<TipoEstatistica>();
	*/
}
