package com.douglas.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Pedido;
import com.douglas.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) throws ObjectNotFoundException {

		return pedidoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Pedido.class.getSimpleName()));
	}
}
