package br.com.ts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_RESPOSTA_ESTATISTICA")
public class TipoRespostaEstatistica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	// ruim | regular | bom | ótimo ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	public TipoRespostaEstatistica() {}

	public TipoRespostaEstatistica(Long id, String nome) {
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
	
	/* O mappedBy informa que o mapeamento já foi feito do outro lado.
	@ManyToMany(mappedBy="tipoRespostas")
	private Set<TipoEstatistica> tipoEstatisticas = new HashSet<TipoEstatistica>();
	*/
}
