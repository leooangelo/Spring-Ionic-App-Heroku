package br.com.leonardoangelo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.leonardoangelo.app.domain.Categoria;
import br.com.leonardoangelo.app.dto.CategoriaDTO;
import br.com.leonardoangelo.app.exception.DataIntegrityException;
import br.com.leonardoangelo.app.exception.ObjectNotFoundException;
import br.com.leonardoangelo.app.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	/**
	 * Metodo para encontrar uma categoria pelo id.
	 * 
	 * @param id
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	/**
	 * 
	 * @param categoria
	 * @return
	 */
	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	/**
	 * 
	 * @param categoria
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public Categoria update(Categoria categoria) throws ObjectNotFoundException {
		Categoria newCategoria = find(categoria.getId());
		newCategoria.setNome(categoria.getNome());
		return categoriaRepository.save(newCategoria);
	}

	/**
	 * Metodo para deletar uma categoria pelo ID
	 * 
	 * @param id
	 * @throws ObjectNotFoundException
	 */
	public void deleteById(Integer id) throws DataIntegrityViolationException {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);

	}
	
	public Categoria paraDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}

}
