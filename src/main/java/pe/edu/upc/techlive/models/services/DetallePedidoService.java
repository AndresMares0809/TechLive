package pe.edu.upc.techlive.models.services;


import java.util.List;

import pe.edu.upc.techlive.models.entities.DetallePedido;

public interface DetallePedidoService extends CrudService<DetallePedido, Integer>{
	List<DetallePedido> findByIsConfirmedFalse() throws Exception;
	Integer countByClienteAndIsConfirmed(Integer id, Boolean isConfirmed) throws Exception;
	List<DetallePedido> findByClienteAndConfirmed(Integer id, Boolean isConfirmed) throws Exception;
}
