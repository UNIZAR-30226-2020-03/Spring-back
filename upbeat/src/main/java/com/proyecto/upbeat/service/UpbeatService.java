package com.proyecto.upbeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.upbeat.model.UpbeatClientModel;
import com.proyecto.upbeat.repository.UpbeatRepository;

@Service
public class UpbeatService implements IUpbeatService{
	
	@Autowired
    private UpbeatRepository repository;

    @Override
    public List<UpbeatClientModel> findAll() {

    	List clientes = (List<UpbeatClientModel>) repository.findAll();

        return clientes;
    }
}
