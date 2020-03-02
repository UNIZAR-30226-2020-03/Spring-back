package com.proyecto.upbeat.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class UpbeatClientModel {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
}
