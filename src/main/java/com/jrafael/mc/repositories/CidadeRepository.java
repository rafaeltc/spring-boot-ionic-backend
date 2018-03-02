package com.jrafael.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrafael.mc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer>{

	
}
