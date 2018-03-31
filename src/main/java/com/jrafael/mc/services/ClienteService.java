package com.jrafael.mc.services;

import java.util.Optional;

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
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Object not found with id = " + id
				+ ", Type = " + Cliente.class.getName()));
	}
}
