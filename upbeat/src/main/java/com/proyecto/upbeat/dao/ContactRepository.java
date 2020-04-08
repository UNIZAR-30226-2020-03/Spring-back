package com.proyecto.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.upbeat.dto.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
