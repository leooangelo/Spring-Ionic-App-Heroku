package br.com.leonardoangelo.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.leonardoangelo.app.domain.Cliente;
import br.com.leonardoangelo.app.service.validation.ClienteUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@ClienteUpdate
public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	@Length(min = 5,max = 80, message ="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	@Email(message = "Email inválido, exemplo de email: teste@1234.com")
	private String email;
	
	public ClienteDTO(Cliente cliente) {
		id=cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}
}
