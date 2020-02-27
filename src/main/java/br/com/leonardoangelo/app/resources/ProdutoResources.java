package br.com.leonardoangelo.app.resources;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardoangelo.app.domain.Produto;
import br.com.leonardoangelo.app.dto.ProdutoDTO;
import br.com.leonardoangelo.app.resources.utils.URL;
import br.com.leonardoangelo.app.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResources {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);

		return ResponseEntity.ok().body(produto);

	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)
			throws UnsupportedEncodingException {
		String nomeDecoded = URL.decodePrarm(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
