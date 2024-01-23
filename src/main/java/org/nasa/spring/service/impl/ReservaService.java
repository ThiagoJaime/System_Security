package org.nasa.spring.service.impl;

import java.util.List;

import org.nasa.spring.dto.ReservaDto;
import org.nasa.spring.entities.Reserva;

public interface ReservaService {
	
	void salvarReserva(ReservaDto ReservaDto);

	Reserva editarReserva(Long id, ReservaDto ReservaDto);

	void deletarReserva(Long id);

	List<ReservaDto> findAllReservas();

	Reserva findById(Long id);
	
}
