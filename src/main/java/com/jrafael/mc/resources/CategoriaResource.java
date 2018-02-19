package com.jrafael.mc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jrafael.mc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1,"Cat1");
		Categoria c2 = new Categoria(2,"Cat2");
		
		List<Categoria> catList = new ArrayList<>();
		catList.add(c1);
		catList.add(c2);
		
		return catList;
	}
}
