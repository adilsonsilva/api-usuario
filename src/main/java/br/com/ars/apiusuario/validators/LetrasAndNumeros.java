package br.com.ars.apiusuario.validators;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import br.com.ars.apiusuario.utils.PadraoLetrasAndNumerosSenha;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PadraoLetrasAndNumerosSenha.class)
public @interface LetrasAndNumeros {

	String message() default "Senha fora do padrão";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    String value() default "";
	
}
