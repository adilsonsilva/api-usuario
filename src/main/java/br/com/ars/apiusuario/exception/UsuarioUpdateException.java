package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioUpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioUpdateException() {
		super();
	}

	public UsuarioUpdateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioUpdateException(String mensagem) {
		super(mensagem);
	}

	public UsuarioUpdateException(Throwable erro) {
		super(erro);
	}

	public UsuarioUpdateException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
}
