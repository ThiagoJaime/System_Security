package org.nasa.spring.dto;

import java.time.LocalDate;

import org.nasa.spring.entities.Cliente;
import org.nasa.spring.entities.Destino;
import org.nasa.spring.entities.Promocao;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaDto {
	
	private Long id;
	
	@NotNull (message = "A data da viagem não pode ser vazia!")
	private LocalDate dataIda;
	
	@NotNull (message = "A data da viagem não pode ser vazia!")
	private LocalDate dataVolta;
	
	@NotNull (message = "O valor não pode ser vazia!")
	private double valor;
	
	@NotNull (message = "O cliente não pode ser vazio!")
	private Cliente cliente;
	
	@NotNull (message = "O destino não pode ser vazio!")
	private Destino destino;
	
	@NotNull (message = "A promocão não pode ser vazio!")
	private Promocao promocao;
	
}
