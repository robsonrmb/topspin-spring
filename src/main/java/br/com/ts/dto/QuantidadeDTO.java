package br.com.ts.dto;

public class QuantidadeDTO {

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
