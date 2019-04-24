package br.com.ars.apiusuario.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import br.com.ars.apiusuario.constantes.Constantes;
import br.com.ars.apiusuario.exception.UsuarioPadraoSenhaException;

/**
 * Classe responsavel por criptgrafar e descriptografar senha
 * 
 * @author adilson
 *
 */
public class Criptografia {

	/**
	 * metodo responsavel por criptografar a senha
	 * 
	 * @param senha
	 *            - senha a criptografar
	 * 
	 * @return String - senha criptografada
	 */
	public String criptografarSenha(String senha) {

		if (StringUtils.isEmpty(senha))
			throw new UsuarioPadraoSenhaException(Constantes.MSG_VALIDACAO_SENHA_NULA);

		BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
		return cript.encode(senha);
	}

	/**
	 * metodo responsavel por validar a senha
	 * 
	 * @param senha
	 *            - senha recebida
	 * @param senhaBase
	 *            - senha da base
	 * @return boolean - valor verdadeiro ou falso para senha valida ou invalida
	 */
	public boolean validarSenha(String senha, String senhaBase) {
		BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
		return cript.matches(senha, senhaBase);
	}

}
