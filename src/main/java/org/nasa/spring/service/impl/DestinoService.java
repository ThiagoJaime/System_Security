package org.nasa.spring.service.impl;

import java.util.List;

import org.nasa.spring.dto.DestinoDto;
import org.nasa.spring.entities.Destino;

public interface DestinoService {
	
	void salvarDestino(DestinoDto destinoDto);

	Destino editarDestino(Long id, DestinoDto destinoDto);

	void deletarDestino(Long id);

	List<DestinoDto> findAllDestinos();

	Destino findById(Long id);
	
}
