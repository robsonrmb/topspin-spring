package br.com.ts.dto;

import java.io.Serializable;

public class RespPerformanceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int ruim;
	private int regular;
	private int bom;
	private int otimo;
	
	public RespPerformanceDTO() {}

	public int getRuim() {
		return ruim;
	}

	public void setRuim(int ruim) {
		this.ruim = ruim;
	}

	public int getRegular() {
		return regular;
	}

	public void setRegular(int regular) {
		this.regular = regular;
	}

	public int getBom() {
		return bom;
	}

	public void setBom(int bom) {
		this.bom = bom;
	}

	public int getOtimo() {
		return otimo;
	}

	public void setOtimo(int otimo) {
		this.otimo = otimo;
	}
	
}
