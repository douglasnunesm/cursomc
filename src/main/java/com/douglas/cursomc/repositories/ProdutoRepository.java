package com.douglas.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.cursomc.domains.Categoria;
import com.douglas.cursomc.domains.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("SELECT produto from Produto produto INNER JOIN produto.categorias cate WHERE cate in  :categorias AND produto.nome LIKE %:nome%")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);
	
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			Pageable pageRequest);


}
