package br.com.ars.apiusuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.ars.apiusuario.constantes.Constantes;
import br.com.ars.apiusuario.responses.Response;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Response> resourceFormatoInvalido(InvalidFormatException ex) {
		return new ResponseEntity<Response>(new Response(Constantes.MSG_VALIDACAO_FORMATO), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<Response> resourceNotFound(JsonParseException ex) {
		return new ResponseEntity<Response>(new Response(Constantes.MSG_ERRO_CONVERSAO_DADOS_ENTRADA),
				HttpStatus.BAD_REQUEST);
	}

}
