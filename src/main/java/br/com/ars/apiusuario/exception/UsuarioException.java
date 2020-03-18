package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = -7857614730742753186L;

	public UsuarioException() {
		super();
	}

	public UsuarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioException(String mensagem) {
		super(mensagem);
	}

	public UsuarioException(Throwable erro) {
		super(erro);
	}

	public UsuarioException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}

}
