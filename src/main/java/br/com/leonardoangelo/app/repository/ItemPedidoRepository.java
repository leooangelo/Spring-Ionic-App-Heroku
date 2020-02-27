package br.com.leonardoangelo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leonardoangelo.app.domain.ItemPedido;
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{


}
