package com.proyecto.upbeat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.proyecto.upbeat.model.UpbeatClientModel;
import com.proyecto.upbeat.model.UpbeatUserModel;
import com.proyecto.upbeat.repository.UpbeatRepository;
import com.proyecto.upbeat.service.IUpbeatService;

@Controller
@RequestMapping("/users")
public class UpbeatController {
	
	@Autowired
	private UpbeatRepository uc;
	
	@RequestMapping(value="", method = RequestMethod.GET)
    public String listaUsuarios(ModelMap mp){
        mp.put("usuarios", uc.findAll());
        return "users/lista";
    }
	
	@RequestMapping(value="/nuevo", method=RequestMethod.GET)
    public String nuevo(ModelMap mp){
        mp.put("usuario", new UpbeatClientModel());
        return "users/nuevo";
    }
 
    @RequestMapping(value="/crear", method=RequestMethod.POST)
    public String crear(@Valid UpbeatClientModel usuario,
            BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
            return "/users/nuevo";
        }else{
            uc.save(usuario);
            mp.put("usuario", usuario);
            return "users/creado";
        }
    }
 
    @RequestMapping(value="/creado", method = RequestMethod.POST)
    public String creado(@RequestParam("usuario") UpbeatClientModel usuario){
        return "/users/creado";
    }
}
