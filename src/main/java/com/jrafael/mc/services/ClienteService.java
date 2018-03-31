package com.jrafael.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Cliente;
import com.jrafael.mc.dto.ClienteDTO;
import com.jrafael.mc.repositories.ClienteRepository;
import com.jrafael.mc.services.exceptions.DataIntegrityException;
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
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		//repo.findOne(id);//just to create the error message in case the entity does not exist
		find(id);
		try {
//			repo.delete(id);
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover Cliente porque há entidades relacionadas");
		}
		
	}

	public List<Cliente> findAll() {
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
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pageRequest = new PageRequest(page,linesPerPage, Direction.valueOf(direction),orderBy);
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	//we only allow the update of name and email
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
