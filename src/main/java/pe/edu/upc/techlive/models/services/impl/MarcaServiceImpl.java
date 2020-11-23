/*package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.Marca;
import pe.edu.upc.techlive.models.repositories.MarcaRepository;
import pe.edu.upc.techlive.models.services.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Transactional
	@Override
	public Marca save(Marca entity) throws Exception {
		return marcaRepository.save(entity);
	}

	@Transactional
	@Override
	public Marca update(Marca entity) throws Exception {
		return marcaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		marcaRepository.deleteById(id);
		
	}

	@Override
	public List<Marca> findAll() throws Exception {
		return marcaRepository.findAll();
	}

	@Override
	public Optional<Marca> findById(Integer id) throws Exception {
		return marcaRepository.findById(id);
	}

	@Override
	public List<Marca> findByNombre(String nombre) throws Exception {
		return marcaRepository.findByNombreContaining(nombre);
	}
}
*/

