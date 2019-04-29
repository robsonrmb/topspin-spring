package br.com.ts.dto;

import java.io.Serializable;

import br.com.ts.domain.TipoAvaliacao;

public class AreaAvaliacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long nome;
	private TipoAvaliacao tipoAvaliacao;
	
	public AreaAvaliacaoDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNome() {
		return nome;
	}

	public void setNome(long nome) {
		this.nome = nome;
	}

	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

}
