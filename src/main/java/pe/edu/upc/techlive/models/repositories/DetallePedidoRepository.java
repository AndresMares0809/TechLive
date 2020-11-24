package pe.edu.upc.techlive.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.techlive.models.entities.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
	List<DetallePedido> findByIsConfirmedFalse() throws Exception;
	
	Integer countByIsConfirmed(Boolean isConfirmed) throws Exception;
	
	
}
