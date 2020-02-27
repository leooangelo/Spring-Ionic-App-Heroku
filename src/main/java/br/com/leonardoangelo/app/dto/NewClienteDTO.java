package br.com.leonardoangelo.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.leonardoangelo.app.service.validation.ClienteInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ClienteInsert
public class NewClienteDTO implements Serializable {

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
	
	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	private String cpfOuCnpj;
	
	private Integer tipo;

	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	private String logradouro;

	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	private String numero;

	private String complemento;

	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatório, o campo não pode ser vazio")
	private String telefone1;

	private String telefone2;

	private String telefone3;

	private Integer cidadeId;

}
