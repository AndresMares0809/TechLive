package pe.edu.upc.techlive.models.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.techlive.models.entities.Pedido;
import pe.edu.upc.techlive.utils.EstadoPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Query("select p from Pedido p where p.cliente.id = ?1 and p.estadoPedido = ?2")
	List<Pedido> findByClienteAndEstadoPedido(Integer id, EstadoPedido estado) throws Exception;
	
	@Query("select p from Pedido p where p.cliente.id = ?1")
	List<Pedido> findByCliente(Integer id) throws Exception;
}
