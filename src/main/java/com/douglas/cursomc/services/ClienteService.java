package com.douglas.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Cliente.class.getSimpleName()));
	}
}
