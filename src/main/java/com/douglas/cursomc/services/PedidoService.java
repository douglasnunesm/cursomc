package com.douglas.cursomc.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.ItemPedido;
import com.douglas.cursomc.domains.PagamentoBoleto;
import com.douglas.cursomc.domains.Pedido;
import com.douglas.cursomc.domains.enums.EstadoPagamento;
import com.douglas.cursomc.repositories.EnderecoRepository;
import com.douglas.cursomc.repositories.ItemPedidoRepository;
import com.douglas.cursomc.repositories.PagamentoRepository;
import com.douglas.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) throws ObjectNotFoundException {

		return pedidoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Pedido.class.getSimpleName()));
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) throws ObjectNotFoundException  {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, pedido.getInstante());
		}
			
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			try {
				item.setProduto(produtoService.find(item.getProduto().getId()));
				item.setPreco(item.getProduto().getPreco());
				item.setPedido(pedido);
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		itemPedidoRepository.saveAll(pedido.getItens());
		pedido.setEndereco(enderecoRepository.findById(pedido.getEndereco().getId()).orElse(null));
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.setPagamento(pagamentoRepository.findById(pedido.getId()).orElse(null));
		emailService.sendOrderConfirmationEmail(pedido);
		return pedido;
	}
}
