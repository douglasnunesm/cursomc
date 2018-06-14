package com.douglas.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.douglas.cursomc.domains.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoBoleto(PagamentoBoleto boleto, Date dataPedido) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataPedido);
		c.set(Calendar.DAY_OF_MONTH, 7);
		boleto.setDataVencimento(c.getTime());
	}
}
