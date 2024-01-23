package org.nasa.spring.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.nasa.spring.dto.PromocaoDto;
import org.nasa.spring.entities.Promocao;
import org.nasa.spring.repository.PromocaoRepository;

@Service
public class PromocaoServiceImpl implements PromocaoService {

	@Autowired
	private PromocaoRepository promocaoRepository;

	@Override
	public List<PromocaoDto> findAllPromocoes() {
		List<Promocao> promocoes = promocaoRepository.findAll();
		return promocoes.stream().map((promocao) -> mapToPromocoesDto(promocao)).collect(Collectors.toList());
	}

	private PromocaoDto mapToPromocoesDto(Promocao promocao) {
		PromocaoDto PromocoesDto = new PromocaoDto();
		PromocoesDto.setId(promocao.getId());
		PromocoesDto.setPrecoPromo(promocao.getPrecoPromo());
		PromocoesDto.setValidadePromo(promocao.getValidadePromo());

		return PromocoesDto;
	}

	@Override
	public void salvarPromocao(PromocaoDto PromocoesDto) {
		
		Promocao promocao = new Promocao();
		promocao.setPrecoPromo(PromocoesDto.getPrecoPromo());
		promocao.setValidadePromo(PromocoesDto.getValidadePromo());

		promocaoRepository.save(promocao);
	}

	@Override
	public Promocao editarPromocao(Long id, PromocaoDto PromocoesDto) {
		Promocao promocao = promocaoRepository.findById(id).orElse(null);

		if (promocao != null) {
			promocao.setPrecoPromo(PromocoesDto.getPrecoPromo());
			promocao.setValidadePromo(PromocoesDto.getValidadePromo());
		}
		promocaoRepository.save(promocao);
		return promocao;
	}

	@Override
	public void deletarPromocao(Long id) {
		promocaoRepository.deleteById(id);
	}

	@Override
	public Promocao findById(Long id) {
		return promocaoRepository.findById(id).orElse(null);
	}

}
