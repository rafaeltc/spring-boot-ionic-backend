package com.jrafael.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.repositories.CategoriaRepository;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria getCategoria(Integer id) {
		Categoria cat = repo.findOne(id);
		
		if(cat == null) {
			throw new ObjectNotFoundException("Object not found with id = " + id
					+ ", Type = " + Categoria.class.getName());
		}
		
		return cat;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
}
