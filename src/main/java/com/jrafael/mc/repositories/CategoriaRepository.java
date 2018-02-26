package com.jrafael.mc.repositories;

import org.springframework.stereotype.Repository;

import com.jrafael.mc.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

	
}
