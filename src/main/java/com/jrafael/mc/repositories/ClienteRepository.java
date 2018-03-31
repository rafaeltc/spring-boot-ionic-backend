package com.jrafael.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrafael.mc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

	//return type , 
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
}
