package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.Pago;
import pe.edu.upc.techlive.models.repositories.PagoRepository;
import pe.edu.upc.techlive.models.services.PagoService;

@Service
public class PagoServiceImpl implements PagoService, Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private PagoRepository pagoRepository;
	
	@Transactional
	@Override
	public Pago save(Pago entity) throws Exception {
		return pagoRepository.save(entity);
	}

	@Transactional
	@Override
	public Pago update(Pago entity) throws Exception {
		return pagoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		pagoRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pago> findAll() throws Exception {
		return pagoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Pago> findById(Integer id) throws Exception {
		return pagoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pago> findByNombreTarjeta(String nombre) throws Exception {
		return pagoRepository.findByNombreTarjeta(nombre);
	}

}
