package br.com.ts.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_AVALIACAO")
public class TipoAvaliacao extends AbstractEntity<Long> {

	// saque | forehand 
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_area_avaliacao", nullable = false)
	private AreaAvaliacao areaAvaliacao;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="TIPOAVALIACAO_TIPORESPOSTAAVALIACAO", 
               joinColumns=  @JoinColumn( name = "tipoavaliacao_id"), 
               inverseJoinColumns= @JoinColumn(name = "tiporesposta_id") )
    private Set<TipoRespostaAvaliacao> tipoRespostas = new HashSet<TipoRespostaAvaliacao>(); 
	
	public TipoAvaliacao() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<TipoRespostaAvaliacao> getTipoRespostas() {
		return tipoRespostas;
	}

	public void setTipoRespostas(Set<TipoRespostaAvaliacao> tipoRespostas) {
		this.tipoRespostas = tipoRespostas;
	}
	
}
