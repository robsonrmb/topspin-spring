package br.com.ts.bean;

import br.com.ts.domain.TipoAvaliacao;

public class FormAreaAvaliacao {

	private long id;
	private long nome;
	private TipoAvaliacao tipoAvaliacao;
	
	public FormAreaAvaliacao() {}

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
