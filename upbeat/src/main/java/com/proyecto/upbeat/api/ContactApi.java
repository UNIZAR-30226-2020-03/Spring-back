package com.proyecto.upbeat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.upbeat.dto.Contact;
import com.proyecto.upbeat.service.ContactService;

@RestController
public class ContactApi {
     
    @RequestMapping(value="/product", method=RequestMethod.GET)
    public Contact getById(){
        return new Contact(1L, "John", "Doe", "+57 311 222 3344", "john@sinbugs.com");
    }
    
    @Autowired
    ContactService contactService;
    
    @RequestMapping(value="/contact", method=RequestMethod.POST)
    public Contact updateOrSave(@RequestBody Contact contact){
        return contactService.save(contact);
    }
}
