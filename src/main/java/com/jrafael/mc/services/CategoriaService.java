package com.jrafael.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.repositories.CategoriaRepository;
import com.jrafael.mc.services.exceptions.DataIntegrityException;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria find(Integer id) {
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
	
	public Categoria update(Categoria obj) {
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		repo.findOne(id);//just to create the error message in case the entity does not exist
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui productos");
		}
		
	}

}
