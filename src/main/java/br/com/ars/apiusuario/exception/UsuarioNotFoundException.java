package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException() {
		super();
	}

	public UsuarioNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioNotFoundException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNotFoundException(Throwable erro) {
		super(erro);
	}

	public UsuarioNotFoundException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
}
