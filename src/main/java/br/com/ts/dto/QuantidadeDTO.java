package br.com.ts.dto;

import java.io.Serializable;

public class QuantidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int quantidade;
	
	public QuantidadeDTO() {}
	
	public QuantidadeDTO(int quantidade) {
		super();
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
