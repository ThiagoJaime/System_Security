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
import org.springframework.web.servlet.ModelAndView;

import org.nasa.spring.dto.ReservaDto;
import org.nasa.spring.repository.ClienteRepository;
import org.nasa.spring.repository.DestinoRepository;
import org.nasa.spring.repository.PromocaoRepository;
import org.nasa.spring.service.impl.ReservaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Autowired
	private DestinoRepository destinoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ModelAndView reservas() {
		ModelAndView modelAndView = new ModelAndView("views/reserva/index");
		modelAndView.addObject("reservas", reservaService.findAllReservas());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("views/reserva/form");
		modelAndView.addObject("reservaDto", new ReservaDto());
		modelAndView.addObject("cliente", clienteRepository.findAll());
		modelAndView.addObject("destino", destinoRepository.findAll());
		modelAndView.addObject("promocao", promocaoRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute @Valid ReservaDto reservaDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("reservaDto", reservaDto);
			model.addAttribute("cliente", clienteRepository.findAll());
			model.addAttribute("destino", destinoRepository.findAll());
			model.addAttribute("promocao", promocaoRepository.findAll());
			
			return "views/reserva/form";
		}

		reservaService.salvarReserva(reservaDto);
		return "redirect:/reservas";
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("views/reserva/form");
		modelAndView.addObject("reservaDto", reservaService.findById(id));
		modelAndView.addObject("cliente", clienteRepository.findAll());
		modelAndView.addObject("destino", destinoRepository.findAll());
		modelAndView.addObject("promocao", promocaoRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @ModelAttribute @Valid ReservaDto reservaDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("reservaDto", reservaDto);
			model.addAttribute("cliente", clienteRepository.findAll());
			model.addAttribute("destino", destinoRepository.findAll());
			model.addAttribute("promocao", promocaoRepository.findAll());
			return "views/reserva/form";
		}

		reservaService.editarReserva(id, reservaDto);
		return "redirect:/reservas";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		reservaService.deletarReserva(id);
		return "redirect:/reservas";
	}
}
