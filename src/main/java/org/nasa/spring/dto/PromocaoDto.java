package org.nasa.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PromocaoDto {
	
	private Long id;
	
	@NotNull (message = "O preço não pode ser vazio!")
	private BigDecimal precoPromo;
	
	@NotNull (message = "A validade da promoção não pode ser vazia!")
	private LocalDate validadePromo;
	
}
