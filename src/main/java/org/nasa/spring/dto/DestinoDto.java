package org.nasa.spring.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DestinoDto {
	
	private Long id;
	
	@NotEmpty (message = "O pais não deve ser vazio!")
	private String pais;
	
	@NotNull (message = "O preço não deve ser vazio!")
	private BigDecimal preco;
	
	@NotNull (message = "A duração não deve ser vazia!")
	private BigInteger duracao;
	
	
}
