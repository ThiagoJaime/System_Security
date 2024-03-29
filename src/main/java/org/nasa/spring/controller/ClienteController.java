package org.nasa.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;

import org.nasa.spring.dto.ClienteDto;
import org.nasa.spring.service.impl.ClienteService;

import java.util.List;

import org.nasa.spring.entities.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String clientes(Model model) {
		
		List<ClienteDto> clientes = clienteService.findAllClientes();
		model.addAttribute("clientes", clientes);
		return "views/cliente/index";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		ClienteDto cliente = new ClienteDto();
		model.addAttribute("cliente", cliente);
		return "views/cliente/form";
	}
	
	@PostMapping("/cadastrar")
	public String cadastroCliente(@Valid @ModelAttribute("cliente") ClienteDto clienteDto, BindingResult result,
			Model model) {
		Cliente existingCliente = clienteService.findClienteByEmail(clienteDto.getEmail());

		if (existingCliente != null && existingCliente.getEmail() != null && !existingCliente.getEmail().isEmpty()) {
			result.rejectValue("email", null, "Já existe uma conta cadastrada com o mesmo email");
		}

		if (result.hasErrors()) {
			model.addAttribute("cliente", clienteDto);
			return "views/cliente/form";
		}

		clienteService.saveCliente(clienteDto);
		return "redirect:/clientes?success";
	}
	
	@GetMapping("/{id}/editar")
	public String editar(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findById(id);
		model.addAttribute("cliente", cliente);
		return "views/cliente/form";
	}
	
	@PostMapping("/{id}/editar")
	public String editarCliente(@PathVariable Long id, @Valid @ModelAttribute("cliente") ClienteDto clienteDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("cliente", clienteDto);
			return "views/cliente/form";
		}

		clienteService.editarCliente(id, clienteDto);

		return "redirect:/clientes?success";
	}
	
	
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return "redirect:/clientes";
	}
	
	
}
