package org.nasa.spring.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.nasa.spring.entities.Cliente;
import org.nasa.spring.repository.ClienteRepository;

@Service
public class CustomClienteDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Cliente cliente = clienteRepository.findByEmail(email);

	    if (cliente == null) {
	        throw new UsernameNotFoundException("Cliente não encontrado com o e-mail: " + email);
	    }

	    return new User(cliente.getEmail(), cliente.getSenha(), new ArrayList<>());
	}
	

}