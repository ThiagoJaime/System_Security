package org.nasa.spring.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.nasa.spring.dto.DestinoDto;
import org.nasa.spring.entities.Destino;
import org.nasa.spring.repository.DestinoRepository;

@Service
public class DestinoServiceImpl implements DestinoService {

	@Autowired
	private DestinoRepository destinoRepository;

	@Override
	public List<DestinoDto> findAllDestinos() {
		List<Destino> destinos = destinoRepository.findAll();
		return destinos.stream().map((destino) -> mapToDestinoDto(destino)).collect(Collectors.toList());
	}

	private DestinoDto mapToDestinoDto(Destino destino) {
		DestinoDto destinoDto = new DestinoDto();
		destinoDto.setId(destino.getId());
		destinoDto.setDuracao(destino.getDuracao());
		destinoDto.setPais(destino.getPais());
		destinoDto.setPreco(destino.getPreco());
		return destinoDto;
	}

	@Override
	public void salvarDestino(DestinoDto destinoDto) {
	    Destino destino = new Destino();
	    
	    destino.setDuracao(destinoDto.getDuracao());
	    destino.setPais(destinoDto.getPais());
	    destino.setPreco(destinoDto.getPreco());

	    destinoRepository.save(destino);
	}

	@Override
	public Destino editarDestino(Long id, DestinoDto destinoDto) {
		Destino destino = destinoRepository.findById(id).orElse(null);

		if (destino != null) {
			
			destino.setDuracao(destinoDto.getDuracao());
		    destino.setPais(destinoDto.getPais());
		    destino.setPreco(destinoDto.getPreco());

			destinoRepository.save(destino);
		}

		return destino;
	}

	@Override
	public void deletarDestino(Long idDestino) {
		destinoRepository.deleteById(idDestino);
	}

	@Override
	public Destino findById(Long id) {
		 return destinoRepository.findById(id).orElse(null);
	}
}
