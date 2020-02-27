package br.com.leonardoangelo.app.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.leonardoangelo.app.domain.PagamentoComBoleto;

@Service
public class BoletoService {
/**
 * 
 * Esta classa não é oficinal para dar data em boletos, para isso devemos usar outro webservice
 * @param pagto
 * @param instanteDoPedido
 */
 	public  void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}
	
	
}
