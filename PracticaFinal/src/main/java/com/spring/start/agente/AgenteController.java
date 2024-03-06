package com.spring.start.agente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.start.agentecliente.AgenteCliente;
import com.spring.start.cliente.Cliente;
import com.spring.start.cliente.ClienteDAO;

import jakarta.validation.Valid;


@Controller
public class AgenteController {

	@Autowired
	AgenteDAO agenteDAO;
	@Autowired
	ClienteDAO clienteDAO;
	
	@GetMapping("/agente")
	public ModelAndView getAgentes() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("agentes");
		List<Agente> agentes = (List<Agente>) agenteDAO.findAll();
		
		model.addObject("agentes", agentes);
		model.addObject("agente", new Agente());
		
		return model;
	}
	
	
	
	@GetMapping("/agente/{id}")
	public ModelAndView getAgente(@PathVariable Long id) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("agente");
		
		Optional<Agente> agente = agenteDAO.findById(id);
		if(agente.isPresent()) {
			model.addObject("agente", agente.get());
			model.addObject("agenteCliente", new AgenteCliente());
			model.addObject("clientes", clienteDAO.findNotLinkCliente(agente.get().getId()));
		}
		
		
		return model;
	}
	
	@GetMapping("/agente/del/{id}")
	public ModelAndView deleteCliente(@PathVariable Long id) {
		
		ModelAndView model = new ModelAndView();
		agenteDAO.deleteById(id);
		model.setViewName("redirect:/agente");
		
		return model;
	}
	
	@GetMapping("/agente/add")
	public ModelAndView addCliente() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("formAgente");
		model.addObject("agente", new Agente());
		
		
		return model;
	}
	
	
	
	
	@PostMapping("/agente/save")
	public ModelAndView saveCliente(@ModelAttribute @Valid Agente agente, BindingResult bindingResult) {
		
		ModelAndView model = new ModelAndView();
		if(bindingResult.hasErrors()) {
			model.setViewName("formAgente");
			model.addObject("agente", agente);
			return model;
		}
		agenteDAO.save(agente);
		
		
		model.setViewName("redirect:/agente");
		
		return model;
	}
	
	@GetMapping("/agente/edit/{id}")
	public ModelAndView editCliente(@PathVariable Long id) {
		
		
		ModelAndView model = new ModelAndView();
		
		Optional<Agente> agente = agenteDAO.findById(id);
		if(agente.isPresent()) {
			model.setViewName("formAgente");
			model.addObject("agente", agente.get());
		}
		else {
			model.setViewName("redirect:/agente");
		}
		
		
		return model;
	}
}
