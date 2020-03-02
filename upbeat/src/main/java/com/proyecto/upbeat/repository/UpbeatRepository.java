package com.proyecto.upbeat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.upbeat.model.UpbeatClientModel;

@Repository
public interface UpbeatRepository extends CrudRepository<UpbeatClientModel, Long>{

}
