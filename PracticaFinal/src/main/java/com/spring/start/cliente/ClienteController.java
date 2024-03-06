package com.spring.start.cliente;

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

import jakarta.validation.Valid;

@Controller
public class ClienteController {

	@Autowired
	ClienteDAO clienteDAO;
	
	@GetMapping("/cliente")
	public ModelAndView getClientes() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("clientes");
		List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
		
		model.addObject("clientes", clientes);
		model.addObject("cliente", new Cliente());
		
		return model;
	}
	
	@GetMapping("/cliente/{id}")
	public ModelAndView getCliente(@PathVariable Long id) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("cliente");
		Optional<Cliente> cliente = clienteDAO.findById(id);
		if(cliente.isPresent()) {
			model.addObject("cliente", cliente.get());
		}
		
		
		return model;
	}
	
	
	
	@GetMapping("/cliente/del/{id}")
	public ModelAndView deleteCliente(@PathVariable Long id) {
		
		ModelAndView model = new ModelAndView();
		clienteDAO.deleteById(id);
		model.setViewName("redirect:/cliente");
		
		return model;
	}
	
	@GetMapping("/cliente/add")
	public ModelAndView addCliente() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("formCliente");
		model.addObject("cliente", new Cliente());
		
		
		return model;
	}
	
	@PostMapping("/cliente/save")
	public ModelAndView saveCliente(@ModelAttribute @Valid Cliente cliente, BindingResult bindingResult) {
		
		ModelAndView model = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			model.setViewName("formCliente");
			model.addObject("cliente", cliente);
			return model;
		}
		
		clienteDAO.save(cliente);
		
		model.setViewName("redirect:/cliente");
		
		return model;
	}
	
	@GetMapping("/cliente/edit/{id}")
	public ModelAndView editCliente(@PathVariable Long id) {
		
		
		ModelAndView model = new ModelAndView();
		
		Optional<Cliente> cliente = clienteDAO.findById(id);
		if(cliente.isPresent()) {
			model.setViewName("formCliente");
			model.addObject("cliente", cliente.get());
		}
		else {
			model.setViewName("redirect:/cliente");
		}
		
		
		return model;
	}
}
