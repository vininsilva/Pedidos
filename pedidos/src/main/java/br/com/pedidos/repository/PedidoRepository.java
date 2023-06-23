package br.com.pedidos.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pedidos.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Optional<Pedido> findByNumeroControle(String numeroControle);

	@Query(value = "SELECT * FROM pedido WHERE DATE(data_cadastro) = DATE(?1)", nativeQuery = true)
	List<Pedido> findByDataCadastro(Date dataCadastro);

}
