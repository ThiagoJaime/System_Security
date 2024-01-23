package org.nasa.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClienteDto {

	private Long id;
	
	@NotEmpty (message = "O nome não deve ser vazio!")
	private String nome;
	
	@NotEmpty (message = "O email não deve ser vazio!")
	@Email
	private String email;
	
	@NotEmpty (message = "A senha não deve ser vazia!")
	private String senha;
	
	@NotEmpty (message = "O telefone não deve ser vazio!")
	private String telefone;
	
}
