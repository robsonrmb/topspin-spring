package br.com.ts.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="AVALIACAO")
public class Avaliacao extends AbstractEntity<Long> {

	@ManyToOne
	@JoinColumn(name="id_usuario_avaliador")
	private Usuario avaliador;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_avaliado")
	private Usuario avaliado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	@OneToMany(mappedBy="avaliacao", cascade = CascadeType.ALL)
	private List<AvaliacaoRespostas> respostas;
	
	public Avaliacao() {}
	
	public Usuario getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}

	public Usuario getAvaliado() {
		return avaliado;
	}

	public void setAvaliado(Usuario avaliado) {
		this.avaliado = avaliado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AvaliacaoRespostas> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<AvaliacaoRespostas> respostas) {
		this.respostas = respostas;
	}

}
