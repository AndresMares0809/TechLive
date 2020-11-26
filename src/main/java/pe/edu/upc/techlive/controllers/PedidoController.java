package pe.edu.upc.techlive.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.entities.Pedido;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.models.services.PedidoService;
import pe.edu.upc.techlive.security.UsuarioDetails;
import pe.edu.upc.techlive.utils.EstadoPedido;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detalleService;

	
	@PostMapping("add")
	public String add(@ModelAttribute("pedido") Pedido pedido, Model model) {
		
	
		try {
			Date date = new Date();
			
			pedido.setFechaPedido(date);
			pedido.setPrecioTotal(pedido.getPrecioTotal() + 15.00);
			pedido.setEstadoPedido(EstadoPedido.PAGADO);
			pedidoService.save(pedido);
			
			for (DetallePedido detalle : pedido.getDetallePedidos()) {
				detalle.setPedido(pedido);
				detalle.setIsConfirmed(true);
				detalleService.update(detalle);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/catalog";
	}
	
	
	@GetMapping("view")
	public String view(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		try {
			List<Pedido> pedidos = pedidoService.findByCliente(usuarioDetails.getIdSegmento());
			model.addAttribute("pedidos", pedidos);
			model.addAttribute("contadorPed", pedidos.size());
			Integer contador = detalleService.countByClienteAndIsConfirmed(usuarioDetails.getIdSegmento(), false);
			model.addAttribute("contador", contador);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "pedidos/view-pedidos";
	}
	
	@GetMapping("{id}/d")
	public String verDetalle( Model model, @PathVariable("id") Integer id) {

		try {
			Optional<Pedido> optional = pedidoService.findById(id);
			model.addAttribute("pedido", optional.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pedidos/view-detalles";
	}


}
