package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.Producto;
import pe.edu.upc.techlive.models.repositories.ProductoRepository;
import pe.edu.upc.techlive.models.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductoRepository productoRepository;
	
	@Transactional
	@Override
	public Producto save(Producto entity) throws Exception {
		return productoRepository.save(entity);
	}

	@Transactional
	@Override
	public Producto update(Producto entity) throws Exception {
		return productoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		productoRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findAll() throws Exception {
		return productoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Producto> findById(Integer id) throws Exception {
		return productoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findByModelo(String modelo) throws Exception {
		return productoRepository.findByModeloContaining(modelo);
	}
}
