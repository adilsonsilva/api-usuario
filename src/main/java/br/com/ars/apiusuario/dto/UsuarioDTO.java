package br.com.ars.apiusuario.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.ars.apiusuario.validators.LetrasAndNumeros;
import io.swagger.annotations.ApiModelProperty;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message = "Não pode ser nulo")
	@Size(min = 3, max = 100, message = "Nome pode conter, minimo de 3 e maximo de 100 caracteres")
	private String nome;

	@NotBlank(message = "Não pode ser nulo")
	@Size(max = 100, message = "E-mail so pode conter 100 caracteres")
	@Email(message = "E-mail informado não e valido")
	private String email;

	@NotBlank(message = "Não pode ser nulo")
	@LetrasAndNumeros
	private String senha;

	private LocalDateTime dataCadastro;

	private LocalDateTime dataExpiracao;
	private Boolean ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dataCadastro="
				+ dataCadastro + ", dataExpiracao=" + dataExpiracao + ", ativo=" + ativo + "]";
	}

}
