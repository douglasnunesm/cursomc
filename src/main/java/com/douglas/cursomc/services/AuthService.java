package com.douglas.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	transient ClienteRepository clienteRepository;

	@Autowired
	transient BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	transient EmailService emailService;

	private Random random = new Random(System.currentTimeMillis());

	public void sendNewPassword(String email) throws ObjectNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("email");
		}

		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));
		clienteRepository.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) {// gera digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {// gera maiuculo
			return (char) (random.nextInt(26) + 65);
		} else {// gera minusculo
			return (char) (random.nextInt(26) + 97);
		}
	}

}
