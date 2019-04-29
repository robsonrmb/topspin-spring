package br.com.ts.dto;

import java.io.Serializable;

public class EstatisticaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long idUsuario;
	private int ano;
	private String nomeTipoAvaliacao;
	
	public EstatisticaDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getNomeTipoAvaliacao() {
		return nomeTipoAvaliacao;
	}

	public void setNomeTipoAvaliacao(String nomeTipoAvaliacao) {
		this.nomeTipoAvaliacao = nomeTipoAvaliacao;
	}
	
}
