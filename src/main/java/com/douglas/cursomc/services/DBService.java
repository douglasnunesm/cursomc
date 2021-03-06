package com.douglas.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.Categoria;
import com.douglas.cursomc.domains.Cidade;
import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.domains.Endereco;
import com.douglas.cursomc.domains.Estado;
import com.douglas.cursomc.domains.ItemPedido;
import com.douglas.cursomc.domains.Pagamento;
import com.douglas.cursomc.domains.PagamentoBoleto;
import com.douglas.cursomc.domains.PagamentoCartao;
import com.douglas.cursomc.domains.Pedido;
import com.douglas.cursomc.domains.Produto;
import com.douglas.cursomc.domains.enums.EstadoPagamento;
import com.douglas.cursomc.domains.enums.Perfil;
import com.douglas.cursomc.domains.enums.TipoCliente;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.repositories.CidadeRepository;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.repositories.EnderecoRepository;
import com.douglas.cursomc.repositories.EstadoRepository;
import com.douglas.cursomc.repositories.ItemPedidoRepository;
import com.douglas.cursomc.repositories.PagamentoRepository;
import com.douglas.cursomc.repositories.PedidoRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;


@Service
public class DBService {
	
	@Autowired
	transient CategoriaRepository categoriaRepository;

	@Autowired
	transient ProdutoRepository produtoRepository;

	@Autowired
	transient EstadoRepository estadoRepository;

	@Autowired
	transient CidadeRepository cidadeRepository;

	@Autowired
	transient ClienteRepository clienteRepository;

	@Autowired
	transient EnderecoRepository enderecoRepository;

	@Autowired
	transient PedidoRepository pedidoRepository;

	@Autowired
	transient PagamentoRepository pagamentoRepository;

	@Autowired
	transient ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	transient BCryptPasswordEncoder encoder;

	
	public void instatiateDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Brasilia");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Taguatinga", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().add(c4);

		Cliente cli1 = new Cliente(null, "Maria Silva", "douglasnunes.m@gmail.com", "36378912377", TipoCliente.PESSOAFISICA,encoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Cliente cli2 = new Cliente(null, "Ruth Borges", "ruthblinhares@gmail.com", "21961635038", TipoCliente.PESSOAFISICA,encoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("223363323", "931212893"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco e1 = new Endereco(null, "Rua Flores", "Jardim", "300", "Apto 203", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "Centro", "105", "Sala 800", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "QI 3", "Taguatinga Norte", "1112", "Altos", "72135030", cli2, c4);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().add(e3);	
		
		estadoRepository.saveAll(Arrays.asList(est1, est2,est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3,c4));
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2,e3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

//		for (int i = 1; i < 100; i++) {
//
//			categoriaRepository.save(new Categoria(null, "Categoria " + i));
//		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 17:35"), e2, cli1);

		Pagamento pagto1 = new PagamentoCartao(null, ped1, EstadoPagamento.QUITADO, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoBoleto(null, ped2, EstadoPagamento.PENDENTE, sdf.parse("20/10/2017 23:59"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
