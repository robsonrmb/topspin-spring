package br.com.ts.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

//@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_ESTATISTICA")
public class TipoEstatistica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	// saque | forehand 
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="TIPOESTATISTICA_TIPORESPOSTAESTATISTICA", 
               joinColumns=  @JoinColumn( name = "tipoestatistica_id"), 
               inverseJoinColumns= @JoinColumn(name = "tiporesposta_id") )
    private Set<TipoRespostaEstatistica> tipoRespostas = new HashSet<TipoRespostaEstatistica>(); 
	
	public TipoEstatistica() {}
	
	public TipoEstatistica(Long id, String nome) {
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

	public Set<TipoRespostaEstatistica> getTipoRespostas() {
		return tipoRespostas;
	}

	public void setTipoRespostas(Set<TipoRespostaEstatistica> tipoRespostas) {
		this.tipoRespostas = tipoRespostas;
	}
	
}
