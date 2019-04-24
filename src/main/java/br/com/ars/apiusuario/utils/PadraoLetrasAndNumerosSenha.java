package br.com.ars.apiusuario.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import br.com.ars.apiusuario.validators.LetrasAndNumeros;

public class PadraoLetrasAndNumerosSenha implements ConstraintValidator<LetrasAndNumeros, String> {
 
    private String value;
 
    @Override
    public void initialize(LetrasAndNumeros constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }
 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    	if (StringUtils.isEmpty(value) || !value.matches("[a-zA-Z0-9]+"))
    		return false;
    	
    	return true;
    }
}
