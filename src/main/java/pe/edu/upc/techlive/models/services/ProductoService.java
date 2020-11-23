package pe.edu.upc.techlive.models.services;

import java.util.List;


import pe.edu.upc.techlive.models.entities.Producto;

public interface ProductoService extends CrudService<Producto, Integer>{
	List<Producto> findByDescripcion(String descripcion) throws Exception;
	List<Producto> findByMarca(String marca) throws Exception;
	List<Producto> findByTag( String tag ) throws Exception;	

}
