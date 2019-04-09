package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UsuarioCadastroException extends RuntimeException {

	public UsuarioCadastroException(String mensagem) {
		super(mensagem);
	}

	public UsuarioCadastroException(Throwable erro) {
		super(erro);
	}

	public UsuarioCadastroException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
}
