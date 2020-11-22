package pe.edu.upc.techlive.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.techlive.models.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
	List<Pago> findByNombreTarjeta(String nombre) throws Exception;
}
