package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioDeleteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioDeleteException() {
		super();
	}

	public UsuarioDeleteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioDeleteException(String mensagem) {
		super(mensagem);
	}

	public UsuarioDeleteException(Throwable erro) {
		super(erro);
	}

	public UsuarioDeleteException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
}
