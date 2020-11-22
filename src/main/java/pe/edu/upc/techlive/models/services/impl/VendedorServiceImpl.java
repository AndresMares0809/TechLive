package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.Vendedor;
import pe.edu.upc.techlive.models.repositories.VendedorRepository;
import pe.edu.upc.techlive.models.services.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService, Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Transactional
	@Override
	public Vendedor save(Vendedor entity) throws Exception {
		return vendedorRepository.save(entity);
	}

	@Transactional
	@Override
	public Vendedor update(Vendedor entity) throws Exception {
		return vendedorRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		vendedorRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Vendedor> findAll() throws Exception {
		return vendedorRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Vendedor> findById(Integer id) throws Exception {
		return vendedorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Vendedor> findByNombre(String nombre) throws Exception {
		return vendedorRepository.findByNombreStartingWith(nombre);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Vendedor> findByDireccion(String direccion) throws Exception {
		return vendedorRepository.findByDireccionContaining(direccion);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Vendedor> findByRuc(String ruc) throws Exception {
		return vendedorRepository.findByRuc(ruc);
	}

}
