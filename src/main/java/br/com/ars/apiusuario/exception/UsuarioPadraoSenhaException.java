package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UsuarioPadraoSenhaException extends RuntimeException {

	public UsuarioPadraoSenhaException(String mensagem) {
		super(mensagem);
	}

	public UsuarioPadraoSenhaException(Throwable erro) {
		super(erro);
	}

	public UsuarioPadraoSenhaException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
}
