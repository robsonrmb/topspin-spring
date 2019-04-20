package br.com.ts.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_ESTATISTICA")
public class TipoEstatistica extends AbstractEntity<Long> {

	// saque | forehand 
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="TIPOESTATISTICA_TIPORESPOSTAESTATISTICA", 
               joinColumns=  @JoinColumn( name = "tipoestatistica_id"), 
               inverseJoinColumns= @JoinColumn(name = "tiporesposta_id") )
    private Set<TipoRespostaEstatistica> tipoRespostas = new HashSet<TipoRespostaEstatistica>(); 
	
	public TipoEstatistica() {}
	
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
