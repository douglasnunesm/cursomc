package com.douglas.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Categoria;
import com.douglas.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService  {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		
		return categoriaRepository.findById(id).orElse(null);
	}
}
