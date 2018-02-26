package com.jrafael.mc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria getCategoria(Integer id) {
		return repo.findOne(id);
	}
}
