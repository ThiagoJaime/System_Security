package org.nasa.spring.service.impl;

import java.util.List;

import org.nasa.spring.entities.Promocao;
import org.nasa.spring.dto.PromocaoDto;

public interface PromocaoService {
	
	void salvarPromocao(PromocaoDto promocaoDto);

	Promocao editarPromocao(Long id, PromocaoDto promocaoDto);

	void deletarPromocao(Long id);

	List<PromocaoDto> findAllPromocoes();

	Promocao findById(Long id);
	
}
