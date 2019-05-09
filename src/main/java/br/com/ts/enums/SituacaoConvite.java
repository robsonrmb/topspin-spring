package br.com.ts.enums;

public enum SituacaoConvite {

	PENDENTE("P", "PENDENTE"),
	ACEITO("A", "ACEITO"),
	RECUSADO("R", "RECUSADO");
	
	private String codigo;
	private String descricao;
	
	private SituacaoConvite(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static SituacaoConvite toEnum(Integer codigo) {
		
		if (codigo == null) {
			return null;
		}
		
		for (SituacaoConvite p: SituacaoConvite.values()) {
			if (codigo.equals(p.getCodigo())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
	
}
