package br.com.leonardoangelo.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EstadoPagamento {
	
	PENDENETE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;

	private String descricao;
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(EstadoPagamento x: EstadoPagamento.values())
			if(cod.equals(x.getCod()))
				return x;
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
