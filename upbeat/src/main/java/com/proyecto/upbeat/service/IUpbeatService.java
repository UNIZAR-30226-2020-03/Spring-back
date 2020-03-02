package com.proyecto.upbeat.service;

import java.util.List;

import com.proyecto.upbeat.model.UpbeatClientModel;

public interface IUpbeatService {
	List<UpbeatClientModel> findAll();
}
