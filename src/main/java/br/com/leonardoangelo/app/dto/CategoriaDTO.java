package br.com.leonardoangelo.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.leonardoangelo.app.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	@Length(min = 5,max = 80, message ="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	
}
