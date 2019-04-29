package br.com.ts.dto;

import java.io.Serializable;
import java.util.Date;

public class AvaliacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long idUsuario;
	private long idAvaliado;
	private Date data;
	private String status;
	
	private String respostaSaque;
	private String respostaForehand;
	private String respostaBackhand;
	private String respostaVoleio;
	private String respostaSmash;
	private String respostaOfensivo;
	private String respostaDefensivo;
	private String respostaTatico;
	private String respostaCompetitivo;
	private String respostaPreparo;
	
	private String nomeUsuario;
	private String nomeAvaliado;
	
	
	public AvaliacaoDTO() {}


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

	public String getRespostaSaque() {
		return respostaSaque;
	}

	public void setRespostaSaque(String respostaSaque) {
		this.respostaSaque = respostaSaque;
	}

	public String getRespostaForehand() {
		return respostaForehand;
	}

	public void setRespostaForehand(String respostaForehand) {
		this.respostaForehand = respostaForehand;
	}

	public String getRespostaBackhand() {
		return respostaBackhand;
	}

	public void setRespostaBackhand(String respostaBackhand) {
		this.respostaBackhand = respostaBackhand;
	}

	public String getRespostaVoleio() {
		return respostaVoleio;
	}

	public void setRespostaVoleio(String respostaVoleio) {
		this.respostaVoleio = respostaVoleio;
	}

	public String getRespostaSmash() {
		return respostaSmash;
	}

	public void setRespostaSmash(String respostaSmash) {
		this.respostaSmash = respostaSmash;
	}

	public String getRespostaOfensivo() {
		return respostaOfensivo;
	}

	public void setRespostaOfensivo(String respostaOfensivo) {
		this.respostaOfensivo = respostaOfensivo;
	}

	public String getRespostaDefensivo() {
		return respostaDefensivo;
	}

	public void setRespostaDefensivo(String respostaDefensivo) {
		this.respostaDefensivo = respostaDefensivo;
	}

	public String getRespostaTatico() {
		return respostaTatico;
	}

	public void setRespostaTatico(String respostaTatico) {
		this.respostaTatico = respostaTatico;
	}

	public String getRespostaCompetitivo() {
		return respostaCompetitivo;
	}

	public void setRespostaCompetitivo(String respostaCompetitivo) {
		this.respostaCompetitivo = respostaCompetitivo;
	}

	public String getRespostaPreparo() {
		return respostaPreparo;
	}

	public void setRespostaPreparo(String respostaPreparo) {
		this.respostaPreparo = respostaPreparo;
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
