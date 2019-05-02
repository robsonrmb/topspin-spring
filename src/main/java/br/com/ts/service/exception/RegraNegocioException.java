package br.com.ts.service.exception;

public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegraNegocioException(String msg) {
		super(msg);
	}
	
	public RegraNegocioException(String msg, Throwable cause) {
		super (msg, cause);
	}
	
}
