package com.jrafael.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.dto.CategoriaDTO;
import com.jrafael.mc.repositories.CategoriaRepository;
import com.jrafael.mc.services.exceptions.DataIntegrityException;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id = " + id
				+ ", Type = " + Categoria.class.getName()));
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
		//repo.findOne(id);//just to create the error message in case the entity does not exist
		find(id);
		try {
//			repo.delete(id);
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui productos");
		}
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	/**
	 * Page and PageRequest are Spring-Data classes
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pageRequest = new PageRequest(page,linesPerPage, Direction.valueOf(direction),orderBy);
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
}
