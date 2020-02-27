package br.com.leonardoangelo.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.leonardoangelo.app.domain.Cidade;
import br.com.leonardoangelo.app.domain.Cliente;
import br.com.leonardoangelo.app.domain.Endereco;
import br.com.leonardoangelo.app.dto.ClienteDTO;
import br.com.leonardoangelo.app.dto.NewClienteDTO;
import br.com.leonardoangelo.app.enums.TipoCliente;
import br.com.leonardoangelo.app.exception.DataIntegrityException;
import br.com.leonardoangelo.app.exception.ObjectNotFoundException;
import br.com.leonardoangelo.app.repository.ClienteRepository;
import br.com.leonardoangelo.app.repository.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	@Autowired
	private EnderecoRepository enderecoRepository;
	/**
	 * Metodo para encontrar um cliente pelo Id.
	 * 
	 * @param id
	 * @return
	 */
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	/**
	 * 
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	/**
	 * Metodo para atualizar dados do cliente
	 * 
	 * @param cliente
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public Cliente update(Cliente cliente) throws ObjectNotFoundException {
		Cliente newCliente = find(cliente.getId());
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
		return clienteRepository.save(newCliente);
	}


	/**
	 * Metodo para deletar um cliente pelo ID
	 * 
	 * @param id
	 * @throws ObjectNotFoundException
	 */
	public void deleteById(Integer id) throws DataIntegrityViolationException {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}

	/**
	 * Listar todos os clientes
	 * 
	 * @return
	 */
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	/**
	 * Paginação na hora de listar usuários json
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);

	}
	
	public Cliente paraDTO(NewClienteDTO newClienteDTO) {
		Cliente cli = new Cliente (null, newClienteDTO.getNome(), newClienteDTO.getEmail(), newClienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(newClienteDTO.getTipo()));
		Cidade cid = new Cidade(newClienteDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null,newClienteDTO.getLogradouro(),newClienteDTO.getNumero(),newClienteDTO.getComplemento(),newClienteDTO.getBairro(),newClienteDTO.getCep(),cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(newClienteDTO.getTelefone1());
			if(newClienteDTO.getTelefone2()!=null)
				cli.getTelefones().add(newClienteDTO.getTelefone2());
			
			if(newClienteDTO.getTelefone3()!=null)
				cli.getTelefones().add(newClienteDTO.getTelefone3());
			
			return cli;
	}

	public Cliente paraDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

}
