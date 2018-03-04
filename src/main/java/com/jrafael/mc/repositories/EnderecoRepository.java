package com.jrafael.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrafael.mc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{

	
}
