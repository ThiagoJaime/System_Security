package org.nasa.spring.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.nasa.spring.dto.ReservaDto;
import org.nasa.spring.entities.Reserva;
import org.nasa.spring.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Override
	public List<ReservaDto> findAllReservas() {
		List<Reserva> Reservas = reservaRepository.findAll();
		return Reservas.stream().map((Reserva) -> mapToReservaDto(Reserva)).collect(Collectors.toList());
	}
	
	private ReservaDto mapToReservaDto(Reserva Reserva) {
		ReservaDto ReservaDto = new ReservaDto();
		ReservaDto.setId(Reserva.getId());
		ReservaDto.setDataIda(Reserva.getDataIda());
		ReservaDto.setDataVolta(Reserva.getDataRetorno());
		ReservaDto.setCliente(Reserva.getCliente());
		ReservaDto.setDestino(Reserva.getDestino());
		ReservaDto.setPromocao(Reserva.getPromocao());
		return ReservaDto;
	}
	
	@Override
	public void salvarReserva(ReservaDto ReservaDto) {
	    Reserva Reserva = new Reserva();
	    
	    Reserva.setDataIda(ReservaDto.getDataIda());
		Reserva.setDataRetorno(ReservaDto.getDataVolta());
		Reserva.setCliente(ReservaDto.getCliente());
		Reserva.setDestino(ReservaDto.getDestino());
		Reserva.setPromocao(ReservaDto.getPromocao());
	    
		reservaRepository.save(Reserva);
	}
	
	@Override
	public Reserva editarReserva(Long id, ReservaDto ReservaDto) {
		Reserva Reserva = reservaRepository.findById(id).orElse(null);

		if (Reserva != null) {
			
			Reserva.setDataIda(ReservaDto.getDataIda());
			Reserva.setDataRetorno(ReservaDto.getDataVolta());
			Reserva.setCliente(ReservaDto.getCliente());
			Reserva.setDestino(ReservaDto.getDestino());
			Reserva.setPromocao(ReservaDto.getPromocao());
		}
		reservaRepository.save(Reserva);
		return Reserva;
	}

	@Override
	public void deletarReserva(Long id) {
		reservaRepository.deleteById(id);
	}

	@Override
	public Reserva findById(Long id) {
		return reservaRepository.findById(id).orElse(null);
	}
}
