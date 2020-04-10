package com.software.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.software.upbeat.model.Cancion;

public interface cancionRepository extends JpaRepository<Cancion, Long> {
}
