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

import org.nasa.spring.dto.PromocaoDto;
import org.nasa.spring.service.impl.PromocaoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {

	@Autowired
	private PromocaoService promocaoService;
	
	@GetMapping
	public ModelAndView promocoes() {
		ModelAndView modelAndView = new ModelAndView("views/promocao/index");
		modelAndView.addObject("promocao", promocaoService.findAllPromocoes());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("views/promocao/form");
		modelAndView.addObject("promocaoDto", new PromocaoDto());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute @Valid PromocaoDto promocaoDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("promocaoDto", promocaoDto);
			return "views/promocao/form";
		}

		promocaoService.salvarPromocao(promocaoDto);
		return "redirect:/promocao";
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("views/promocao/form");
		modelAndView.addObject("promocaoDto", promocaoService.findById(id));
		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @ModelAttribute @Valid PromocaoDto promocaoDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("promocaoDto", promocaoDto);
			return "views/promocao/form";
		}

		promocaoService.editarPromocao(id, promocaoDto);
		return "redirect:/promocao";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		promocaoService.deletarPromocao(id);
		return "redirect:/promocao";
	}
}
