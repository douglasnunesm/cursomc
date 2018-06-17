package com.douglas.cursomc.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Cidade;
import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.domains.Endereco;
import com.douglas.cursomc.domains.enums.Perfil;
import com.douglas.cursomc.domains.enums.TipoCliente;
import com.douglas.cursomc.dto.ClienteDTO;
import com.douglas.cursomc.dto.ClienteNewDTO;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.repositories.EnderecoRepository;
import com.douglas.cursomc.security.UserSS;
import com.douglas.cursomc.services.exceptions.AuthorizationException;
import com.douglas.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	transient ClienteRepository clienteRepository;

	@Autowired
	transient EnderecoRepository enderecoRepository;

	@Autowired
	transient BCryptPasswordEncoder encoder;

	public Cliente find(Integer id) throws ObjectNotFoundException {

		UserSS user = UserService.authenticated();
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		if(cliente != null) {
			
		}
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Cliente.class.getSimpleName()));
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;

	}

	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		Cliente cliente = find(obj.getId());
		updateData(obj, cliente);
		return clienteRepository.save(cliente);
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

	public Cliente fromDTO(ClienteNewDTO dto) {

		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfCpnj(),
				TipoCliente.getEnumByCod(dto.getTipo()), encoder.encode(dto.getSenha()));

		Cidade cidade = new Cidade(dto.getIdCidade(), null);
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getBairro(), dto.getNumero(),
				dto.getComplemento(), dto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);

		dto.getTelefones().forEach(item -> cliente.getTelefones().add(item));

		return cliente;
	}

	private void updateData(Cliente antigo, Cliente novo) {
		novo.setNome(antigo.getNome());
		novo.setEmail(antigo.getEmail());
	}
}
