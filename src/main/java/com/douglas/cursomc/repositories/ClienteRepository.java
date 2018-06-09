package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domains.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	
}
