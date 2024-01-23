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

import org.nasa.spring.dto.DestinoDto;
import org.nasa.spring.service.impl.DestinoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/destino")
public class DestinoController {
	@Autowired
	private DestinoService destinoService;

	@GetMapping
	public ModelAndView destinos() {
		ModelAndView modelAndView = new ModelAndView("views/destino/index");
		modelAndView.addObject("destinos", destinoService.findAllDestinos());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		
		ModelAndView modelAndView = new ModelAndView("views/destino/form");
		modelAndView.addObject("destinoDto", new DestinoDto());
		
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute @Valid DestinoDto destinoDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("destinoDto", destinoDto);
			
			return "views/destino/form";
		}

		destinoService.salvarDestino(destinoDto);
		return "redirect:/destino";
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("views/destino/form");
		modelAndView.addObject("destinoDto", destinoService.findById(id));
		
		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @ModelAttribute @Valid DestinoDto destinoDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("destinoDto", destinoDto);
			
			return "views/destino/form";
		}

		destinoService.editarDestino(id, destinoDto);
		return "redirect:/destino";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		destinoService.deletarDestino(id);
		return "redirect:/destino";
	}
}
