package pe.edu.upc.techlive.models.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.repositories.DetallePedidoRepository;
import pe.edu.upc.techlive.models.services.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService, Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	
	@Transactional
	@Override
	public DetallePedido save(DetallePedido entity) throws Exception {
		return detallePedidoRepository.save(entity);
	}

	@Transactional
	@Override
	public DetallePedido update(DetallePedido entity) throws Exception {
		return detallePedidoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		detallePedidoRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<DetallePedido> findAll() throws Exception {
		return detallePedidoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<DetallePedido> findById(Integer id) throws Exception {
		return detallePedidoRepository.findById(id);
	}

	@Override
	public List<DetallePedido> findByIsConfirmedFalse() throws Exception {
		return detallePedidoRepository.findByIsConfirmedFalse();
	}

	@Override
	public Integer countByIsConfirmed(Boolean isConfirmed) throws Exception {
		return detallePedidoRepository.countByIsConfirmed(isConfirmed);
	}





	
}
