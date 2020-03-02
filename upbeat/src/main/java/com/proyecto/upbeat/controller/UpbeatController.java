package com.proyecto.upbeat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.upbeat.model.UpbeatClientModel;
import com.proyecto.upbeat.service.IUpbeatService;

@Controller
public class UpbeatController {
	
	@Autowired
	private IUpbeatService clienteService;
	
	@GetMapping("/mostrarClientes")
	public String findClientes(Model model) {
		
		List cliente = (List<UpbeatClientModel>) clienteService.findAll();
		
		model.addAttribute("clientes", cliente);
		
		return "mostrarClientes";
	}
}
