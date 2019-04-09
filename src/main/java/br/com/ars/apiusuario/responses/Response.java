/**
 * 
 */
package br.com.ars.apiusuario.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author adilson
 *
 */
public class Response<T> {

	@JsonInclude(Include.NON_EMPTY)
	private T data;

	@JsonInclude(Include.NON_EMPTY)
	private String mensagemErro;

	@JsonInclude(Include.NON_EMPTY)
	private List<String> errors;

	@JsonInclude(Include.NON_EMPTY)
	private String mensagemSucesso;

	public Response() {
	}
	
	public Response(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getMensagemSucesso() {
		return mensagemSucesso;
	}

	public void setMensagemSucesso(String mensagemSucesso) {
		this.mensagemSucesso = mensagemSucesso;
	}

}
