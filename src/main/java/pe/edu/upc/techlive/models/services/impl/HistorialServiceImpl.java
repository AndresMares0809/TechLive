package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.Historial;
import pe.edu.upc.techlive.models.repositories.HistorialRepository;
import pe.edu.upc.techlive.models.services.HistorialService;

@Service
public class HistorialServiceImpl implements HistorialService, Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private HistorialRepository historialRepository;
	
	@Transactional
	@Override
	public Historial save(Historial entity) throws Exception {
		return historialRepository.save(entity);
	}

	@Transactional
	@Override
	public Historial update(Historial entity) throws Exception {
		return historialRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		historialRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Historial> findAll() throws Exception {
		return historialRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Historial> findById(Integer id) throws Exception {
		return historialRepository.findById(id);
	}

}
