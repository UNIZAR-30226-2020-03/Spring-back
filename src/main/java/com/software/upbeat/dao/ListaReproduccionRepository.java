package com.software.upbeat.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.software.upbeat.model.ListaReproduccion;

@Repository
public interface ListaReproduccionRepository extends JpaRepository<ListaReproduccion, Long>{

}
