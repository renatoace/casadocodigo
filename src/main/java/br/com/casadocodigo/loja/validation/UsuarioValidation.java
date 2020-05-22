package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		

		
		
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senhaRepetida", "field.required");
		
		Usuario usuario = (Usuario) target;

		if (usuario.getSenha().isEmpty() || usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "invalid.password");
		}
		
		if (usuario.getSenhaRepetida().isEmpty()) {
			errors.rejectValue("senhaRepetida", "field.required");
		} else if (!usuario.getSenha().equals(usuario.getSenhaRepetida())) {
			errors.rejectValue("senhaRepetida", "invalid.password");
		}
	}
	
}
