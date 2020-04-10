package com.software.upbeat;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.content.commons.renditions.Renderable;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.data.repository.CrudRepository;

import com.software.upbeat.model.SopCanciones;

@SpringBootApplication
public class UpbeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpbeatApplication.class, args);
	}
	
	public interface SopCancionesRepository extends CrudRepository<SopCanciones, Long> {
	}
	
	public interface SopCancionesContentStore extends ContentStore<SopCanciones, UUID>, Renderable<SopCanciones> {
	}
}
