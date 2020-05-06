package com.software.upbeat.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.software.upbeat.dao.ClienteRepository;
import com.software.upbeat.exception.ResourceNotFoundException;
import com.software.upbeat.model.Cliente;
import com.software.upbeat.service.ClienteService;

@RestController
@RequestMapping("/cliente/")
public class ClienteApi {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	Mapper mapper;
	
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR EMAIL				//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{correo}", method=RequestMethod.GET)
	public ClienteResponse getByEmail(@PathVariable(value = "correo") String correoCliente) {
	
		// Mapeo request dto
		// Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteByEmail.getBody(), ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR PASSWORD Y EMAIL 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{contrasenya}/{correo}", method=RequestMethod.GET)
	public ClienteResponse getByEmailAndPassword(@PathVariable(value = "contrasenya") String password, @PathVariable(value = "correo") String correoCliente) {
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmailAndPassword = clienteService.getClienteByEmailAndPassword(password, correoCliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteByEmailAndPassword.getBody(), ClienteResponse.class);
		
		return clienteResponse;
	}
	//////////////////////////////////////////////
	// OBTENER TODOS LOS CLIENTES				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allClientes", method=RequestMethod.GET)
	public List<Cliente> getAllClientes() {
		return clienteService.getAllClientes();
	}
	
	//////////////////////////////////////////////
	// AÑADIR CLIENTE							//
	//////////////////////////////////////////////
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ClienteResponse saveCliente(@RequestBody ClienteRequest clienteRequest) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		Cliente newCliente = clienteService.save(cliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(newCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR CLIENTE POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{correo}", method=RequestMethod.PUT)
	public ClienteResponse update(@PathVariable(value = "correo") String correoCliente,
			@Valid @RequestBody ClienteRequest datosCliente) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(datosCliente, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente updateCliente = clienteByEmail.getBody();
		
		updateCliente.setNombre(cliente.getNombre());
		updateCliente.setApellidos(cliente.getApellidos());
		updateCliente.setContrasenya(cliente.getContrasenya());
		updateCliente.setCorreo(cliente.getCorreo());
		updateCliente.setUsername(cliente.getUsername());
		updateCliente.setPais(cliente.getPais());
		
		updateCliente = clienteService.save(updateCliente);
		
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(updateCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ELIMINAR CLIENTE					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{correo}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "correo") String correoCliente) {
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente deleteCliente = clienteByEmail.getBody();
		
		clienteService.delete(deleteCliente);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// ClienteResponse clienteResponse = mapper.map(deleteCliente, ClienteResponse.class);
		
		return response;
		
		
	}
	
	//////////////////////////////////////////////
	// SUBIR FOTO DE PERFIL				 		//
	//////////////////////////////////////////////
	
	@PostMapping("/subirFoto/{correo}/{foto}")
	public BodyBuilder uplaodImage(@PathVariable(value = "correo") String correoCliente, @RequestParam("foto") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ResponseEntity<Cliente> clienteFoto = clienteService.getClienteByEmail(correoCliente);
		
		Cliente fotoCliente = clienteFoto.getBody();
		
		fotoCliente.setPicByte(compressBytes(file.getBytes()));
		clienteService.save(fotoCliente);
		/*
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);*/
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	//////////////////////////////////////////////
	// RECUPERAR FOTO DE PERFIL			 		//
	//////////////////////////////////////////////
	@GetMapping(path = { "/obtenerFoto/{correo}" })
	public Cliente getImage(@PathVariable("correo") String correo) throws IOException {

		final Optional<Cliente> retrievedImage = clienteRepository.findPhotoByEmail(correo);
		Cliente cliente = retrievedImage.get();
		cliente.setPicByte(decompressBytes(retrievedImage.get().getPicByte()));
		/*
		Cliente cliente = new Cliente(retrievedImage.get().getCod_cliente(),retrievedImage.get().getNombre(), 
				retrievedImage.get().getApellidos(),retrievedImage.get().getContrasenya(),
				retrievedImage.get().getCorreo(),retrievedImage.get().getUsername(),retrievedImage.get().getPais(),
				decompressBytes(retrievedImage.get().getPicByte()));*/
		return cliente;
	}
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
}
