package com.software.upbeat;



import java.io.FileInputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.context.annotation.Bean;


import com.software.upbeat.dao.cancionRepository;
import com.software.upbeat.model.Cancion;


@SpringBootApplication
public class UpbeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpbeatApplication.class, args);
	}
	
	public interface cancionStore extends ContentStore<Cancion, String> {
	}
	
	@Bean
	public CommandLineRunner demo( cancionRepository repository, cancionStore store) {
		return (args) -> {
			// create a new user
			Cancion bellaCiao = new Cancion("bellaCiao");

			// store profile picture
			store.setContent(bellaCiao, new FileInputStream("C:\\Users\\victo\\Music\\Talco\\10 Years\\16 Bella ciao.mp3"));

			// save the user
			repository.save(bellaCiao);
		};
	}
}
