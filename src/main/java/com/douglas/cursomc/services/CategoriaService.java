package com.douglas.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Categoria;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) throws ObjectNotFoundException {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Categoria.class.getSimpleName()));
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
			
	}

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categorias possui produtos.");
		}
	}
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
}
