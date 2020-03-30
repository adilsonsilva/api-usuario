/**
 * 
 */
package br.com.ars.apiusuario.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author adilson
 *
 */
@Getter
@Setter
@RequiredArgsConstructor
public class Response<T> {

	@JsonInclude(Include.NON_EMPTY)
	private T data;

	@JsonInclude(Include.NON_EMPTY)
	private String mensagemErro;

	@JsonInclude(Include.NON_EMPTY)
	private List<String> errors = new ArrayList<>();

	@JsonInclude(Include.NON_EMPTY)
	private String mensagemSucesso;

	public Response(T data) {
		super();
		this.data = data;
	}

}
