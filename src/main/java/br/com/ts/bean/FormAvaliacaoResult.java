package br.com.ts.bean;

import java.util.Date;
import java.util.List;

public class FormAvaliacaoResult {

	private long id;
	private long idUsuario;
	private long idAvaliado;
	private Date data;
	private String status;
	
	private List<String> respostas;
	
	private String nomeUsuario;
	private String nomeAvaliado;
	
	public FormAvaliacaoResult() {}


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

	public long getIdAvaliado() {
		return idAvaliado;
	}

	public void setIdAvaliado(long idAvaliado) {
		this.idAvaliado = idAvaliado;
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

	public List<String> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<String> respostas) {
		this.respostas = respostas;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeAvaliado() {
		return nomeAvaliado;
	}

	public void setNomeAvaliado(String nomeAvaliado) {
		this.nomeAvaliado = nomeAvaliado;
	}

}
