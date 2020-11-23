package pe.edu.upc.techlive.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.techlive.models.entities.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer>{

	
}
