package br.com.ars.apiusuario.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.ars.apiusuario.constantes.Constantes;
import br.com.ars.apiusuario.responses.Response;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final String LABEL_CAMPO = "Campo: ";

	private static final String LABEL_ERRO = " - Erro: ";

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Response> resourceFormatoInvalido(InvalidFormatException ex) {
		return new ResponseEntity<Response>(new Response<String>(Constantes.MSG_VALIDACAO_FORMATO),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<Response> resourceNotFound(JsonParseException ex) {
		return new ResponseEntity<Response>(new Response<String>(Constantes.MSG_ERRO_CONVERSAO_DADOS_ENTRADA),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Response<List<String>> response = new Response<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();

			StringBuilder sb = new StringBuilder(LABEL_CAMPO);
			sb.append(fieldName).append(LABEL_ERRO).append(errorMessage);

			response.getErrors().add(sb.toString());
		});
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

}
