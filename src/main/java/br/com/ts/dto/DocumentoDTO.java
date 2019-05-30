package br.com.ts.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Date dataHoraUpload;	

	@JsonProperty
	private String tipoDocumento;

	@JsonProperty
	private String nome;	

	@JsonProperty
	private String mimeType;	

	@JsonProperty
	private byte[] arquivo;
    
	public Date getDataHoraUpload() {
		return dataHoraUpload;
	}

	public void setDataHoraUpload(Date dataHoraUpload) {
		this.dataHoraUpload = dataHoraUpload;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

}
