package com.jrafael.mc.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		
		LocalDateTime date = instanteDoPedido.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		date.plus(7, ChronoUnit.DAYS);
		pagto.setDataVencimento(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(instanteDoPedido);
//		cal.add(Calendar.DAY_OF_MONTH, 7);
//		pagto.setDataVencimento(cal.getTime());
	}
	
}
