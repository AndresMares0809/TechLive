package pe.edu.upc.techlive.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.techlive.models.entities.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
	List<DetallePedido> findByIsConfirmedFalse() throws Exception;
	
	@Query("select count(dp) from DetallePedido dp where dp.cliente.id = ?1 and dp.isConfirmed = ?2")
	Integer countByClienteAndIsConfirmed(Integer id, Boolean isConfirmed) throws Exception;
	
	@Query("select dp from DetallePedido dp where dp.cliente.id = ?1 and dp.isConfirmed = ?2")
	List<DetallePedido> findByClienteAndConfirmed(Integer id, Boolean isConfirmed) throws Exception;
	
}
