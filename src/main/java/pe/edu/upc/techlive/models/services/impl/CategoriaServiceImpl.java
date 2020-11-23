package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.Categoria;
import pe.edu.upc.techlive.models.repositories.CategoriaRepository;
import pe.edu.upc.techlive.models.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional
	@Override
	public Categoria save(Categoria entity) throws Exception {
		return categoriaRepository.save(entity);
	}

	@Transactional
	@Override
	public Categoria update(Categoria entity) throws Exception {
		return categoriaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		categoriaRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Categoria> findAll() throws Exception {
		return categoriaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Categoria> findById(Integer id) throws Exception {
		return categoriaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Categoria> findByDenominacion(String denominacion) throws Exception {
		return categoriaRepository.findByDenominacionContaining(denominacion);
	}
	

}
