package com.douglas.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.dto.ClienteDTO;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Cliente.class.getSimpleName()));
	}
	

	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		find(obj.getId());
		return clienteRepository.save(obj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categorias possui produtos.");
		}
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findAllPage(Integer page, Integer linesPerPage, String orderBy, Direction direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail());
	}
}
