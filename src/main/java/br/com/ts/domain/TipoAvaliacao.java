package br.com.ts.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

//@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_AVALIACAO")
public class TipoAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
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

	public Set<TipoRespostaAvaliacao> getTipoRespostas() {
		return tipoRespostas;
	}

	public void setTipoRespostas(Set<TipoRespostaAvaliacao> tipoRespostas) {
		this.tipoRespostas = tipoRespostas;
	}
	
}
