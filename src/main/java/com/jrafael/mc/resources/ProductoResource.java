package com.jrafael.mc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jrafael.mc.domain.Producto;
import com.jrafael.mc.dto.ProductoDTO;
import com.jrafael.mc.resources.utils.URL;
import com.jrafael.mc.services.ProductoService;

@RestController
@RequestMapping(value="/productos")
public class ProductoResource {

	@Autowired
	private ProductoService productoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Producto> find(@PathVariable Integer id) {
		Producto prod = productoService.find(id);
		return ResponseEntity.ok().body(prod);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Producto> list = productoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductoDTO> listDto = list.map(obj -> new ProductoDTO(obj)); 
		return ResponseEntity.ok().body(listDto);
	}
}
