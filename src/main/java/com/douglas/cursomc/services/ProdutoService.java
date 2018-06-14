package com.douglas.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Categoria;
import com.douglas.cursomc.domains.Produto;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) throws ObjectNotFoundException {

		return produtoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Produto.class.getSimpleName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, Direction direction) {

		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome,categorias, pageRequest);
	}
}
