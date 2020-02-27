package br.com.leonardoangelo.app.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leonardoangelo.app.domain.Cliente;
import br.com.leonardoangelo.app.dto.ClienteDTO;
import br.com.leonardoangelo.app.dto.NewClienteDTO;
import br.com.leonardoangelo.app.service.ClienteService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteService clienteService;;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = clienteService.find(id);

		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody NewClienteDTO newClienteDTO) {
		Cliente cliente = clienteService.paraDTO(newClienteDTO);

		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id)
			throws ObjectNotFoundException {
		Cliente cliente = clienteService.paraDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Deletar uma cliente
	 * 
	 * @param id
	 * @return
	 * @throws ObjectNotFoundException
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws ObjectNotFoundException {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Listar todas clientes
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
