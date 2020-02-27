package br.com.leonardoangelo.app.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.leonardoangelo.app.domain.Cliente;
import br.com.leonardoangelo.app.dto.NewClienteDTO;
import br.com.leonardoangelo.app.enums.TipoCliente;
import br.com.leonardoangelo.app.exception.FieldMessage;
import br.com.leonardoangelo.app.repository.ClienteRepository;
import br.com.leonardoangelo.app.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(NewClienteDTO newClienteDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (newClienteDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(newClienteDTO.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));

		if (newClienteDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(newClienteDTO.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		
		Cliente aux = clienteRepository.findByEmail(newClienteDTO.getEmail());
		
		if(aux != null)
			list.add(new FieldMessage("email","Email ja cadastrado"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}