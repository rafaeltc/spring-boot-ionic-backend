package com.jrafael.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.domain.Pedido;
import com.jrafael.mc.domain.Producto;
import com.jrafael.mc.repositories.CategoriaRepository;
import com.jrafael.mc.repositories.ProductoRepository;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductoService {

	@Autowired
	public ProductoRepository repo;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Producto find(Integer id) {
		Optional<Producto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id = " + id
				+ ", Type = " + Pedido.class.getName()));
	}
	
	public Page<Producto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistictByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
