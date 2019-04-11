package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class UsuarioCadastradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioCadastradoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioCadastradoException(Throwable erro) {
		super(erro);
	}

	public UsuarioCadastradoException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
}
