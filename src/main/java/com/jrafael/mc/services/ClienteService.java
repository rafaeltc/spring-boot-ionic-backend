package com.jrafael.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Cliente;
import com.jrafael.mc.repositories.ClienteRepository;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente cat = repo.findOne(id);
		
		if(cat == null) {
			throw new ObjectNotFoundException("Object not found with id = " + id
					+ ", Type = " + Cliente.class.getName());
		}
		
		return cat;
	}
}
