package pe.edu.upc.techlive.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.entities.Pedido;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.models.services.PedidoService;
import pe.edu.upc.techlive.security.UsuarioDetails;
import pe.edu.upc.techlive.utils.EstadoPedido;



@Controller
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	DetallePedidoService detalleService;
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("view")
	public String view(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			List<DetallePedido> detalles = detalleService.findByVendedorAndConfirmed(usuarioDetails.getIdSegmento(), true);
			model.addAttribute("detallePedidos", detalles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "entregas/view-entregas";
	}
	
	@GetMapping("{id}/enviar")
	public String send(@PathVariable("id") Integer id, Model model) {
	
		
		try {
			Optional<Pedido> pedido = pedidoService.findById(id);
			pedido.get().setEstadoPedido(EstadoPedido.ENRUTA);
			pedidoService.update(pedido.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/entregas/view";
	}
}
