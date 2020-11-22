package pe.edu.upc.techlive.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.techlive.models.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByDescripcionContaining(String descripcion) throws Exception;
	List<Producto> findByMarcaContaining( String marca ) throws Exception;	
	List<Producto> findByTagContaining( String tag ) throws Exception;	

}
