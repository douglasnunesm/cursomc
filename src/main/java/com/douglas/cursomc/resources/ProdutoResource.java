package com.douglas.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.cursomc.domains.Produto;
import com.douglas.cursomc.dto.ProdutoDTO;
import com.douglas.cursomc.resources.utils.URL;
import com.douglas.cursomc.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Produto produto = service.find(id);
		return ResponseEntity.ok(produto);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> search(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)  {
		
		List<Integer> listaCategorias = URL.decodeToListInteger(categorias);
		Page<Produto> lista = service.search(URL.decodeString(nome), listaCategorias, page, linesPerPage, orderBy, Direction.valueOf(direction));
		Page<ProdutoDTO> clientesRetorno = lista.map(item -> new ProdutoDTO(item));

		return ResponseEntity.ok().body(clientesRetorno);
	}
}
