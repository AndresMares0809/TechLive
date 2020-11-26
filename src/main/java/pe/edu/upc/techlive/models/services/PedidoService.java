package pe.edu.upc.techlive.models.services;


import java.util.List;

import pe.edu.upc.techlive.models.entities.Pedido;
import pe.edu.upc.techlive.utils.EstadoPedido;

public interface PedidoService extends CrudService<Pedido, Integer>{
	List<Pedido> findByClienteAndEstadoPedido(Integer id, EstadoPedido estado) throws Exception;
	List<Pedido> findByCliente(Integer id) throws Exception;
}
