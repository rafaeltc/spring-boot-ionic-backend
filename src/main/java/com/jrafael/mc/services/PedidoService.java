package com.jrafael.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Pedido;
import com.jrafael.mc.repositories.PedidoRepository;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id = " + id
				+ ", Type = " + Pedido.class.getName()));
	}
}
