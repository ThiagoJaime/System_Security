package org.nasa.spring.service.impl;

import java.util.List;

import org.nasa.spring.dto.ClienteDto;
import org.nasa.spring.entities.Cliente;

public interface ClienteService {
	
	void saveCliente(ClienteDto clienteDto);

	Cliente findClienteByEmail(String email);

	List<ClienteDto> findAllClientes();

	void deletarCliente(Long id);
	
	Cliente findById(Long id);

	Cliente editarCliente(Long id, ClienteDto clienteDto);
	
}
