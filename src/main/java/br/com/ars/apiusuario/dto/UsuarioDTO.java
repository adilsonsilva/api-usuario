package br.com.ars.apiusuario.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.ars.apiusuario.validators.LetrasAndNumeros;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "senha")
public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Id da pessoa.", required = false)
	private Integer id;

	@ApiModelProperty(value = "Nome da pessoa.", required = false)
	@NotBlank(message = "Não pode ser nulo")
	@Size(min = 3, max = 100, message = "Nome pode conter, minimo de 3 e maximo de 100 caracteres")
	private String nome;

	@ApiModelProperty(value = "E-mail da pessoa.", required = false)
	@NotBlank(message = "Não pode ser nulo")
	@Size(max = 100, message = "E-mail so pode conter 100 caracteres")
	@Email(message = "E-mail informado não e valido")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ApiModelProperty(value = "Senha da pessoa.", required = false)
	@NotBlank(message = "Não pode ser nulo")
	@LetrasAndNumeros
	private String senha;

	@ApiModelProperty(value = "Data e hora de cadastro da pessoa.", required = false)
	private LocalDateTime dataCadastro;

	@ApiModelProperty(value = "Data e hora de expiração da pessoa.", required = false)
	private LocalDateTime dataExpiracao;

	@ApiModelProperty(value = "Flag que indica se a pessoa esta ativa ou não.", required = false)
	private Boolean ativo;

}
