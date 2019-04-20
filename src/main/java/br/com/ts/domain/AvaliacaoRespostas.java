package br.com.ts.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="AVALIACAO_RESPOSTAS")
public class AvaliacaoRespostas extends AbstractEntity<Long> {

	@ManyToOne
	@JoinColumn(name="id_avaliacao", nullable = false)
	private Avaliacao avaliacao;
	
	@Column(name = "id_tipo_avaliacao", nullable = false)
	private long idTipoAvaliacao;
	
	@Column(name = "id_tipo_resposta", nullable = false)
	private long idTipoResposta;
	
	/*
	@ManyToOne
	@JoinColumn(name="id_tipo_avaliacao", nullable = false)
	private TipoAvaliacao tipoAvaliacao;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_resposta", nullable = false)
	private TipoResposta tipoResposta;
	*/
	
	public AvaliacaoRespostas() {}
	
	public AvaliacaoRespostas(Avaliacao avaliacao, long idTipoAvaliacao, long idTipoResposta) {
		super();
		this.avaliacao = avaliacao;
		this.idTipoAvaliacao = idTipoAvaliacao;
		this.idTipoResposta = idTipoResposta;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public long getIdTipoAvaliacao() {
		return idTipoAvaliacao;
	}

	public void setIdTipoAvaliacao(long idTipoAvaliacao) {
		this.idTipoAvaliacao = idTipoAvaliacao;
	}

	public long getIdTipoResposta() {
		return idTipoResposta;
	}

	public void setIdTipoResposta(long idTipoResposta) {
		this.idTipoResposta = idTipoResposta;
	}
	
	/*
	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

	public TipoResposta getTipoResposta() {
		return tipoResposta;
	}

	public void setTipoResposta(TipoResposta tipoResposta) {
		this.tipoResposta = tipoResposta;
	}
	*/
	
}
