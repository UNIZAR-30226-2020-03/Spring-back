package com.proyecto.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.upbeat.dao.ContactRepository;
import com.proyecto.upbeat.dto.Contact;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository dao;
	
	public Contact save(Contact contact) {
		return dao.saveAndFlush(contact);
	}

}
