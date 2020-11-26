package pe.edu.upc.techlive.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.entities.Pedido;
import pe.edu.upc.techlive.models.services.ClienteService;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.security.UsuarioDetails;



@Controller
@RequestMapping("/cart")
@SessionAttributes("{contador}")
public class CarritoController {
	
	@Autowired
	private DetallePedidoService detalleService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String inicio(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		
		try {
			List<DetallePedido> detalles = detalleService.findByClienteAndConfirmed(usuarioDetails.getIdSegmento(), false);
			model.addAttribute("detallePedidos", detalles);
			Integer contador = detalleService.countByClienteAndIsConfirmed(usuarioDetails.getIdSegmento(), false);
			model.addAttribute("contador", contador);
			Pedido pedido = new Pedido();
		
			pedido.setDetallePedidos(detalles);
			pedido.setCliente(clienteService.findById(usuarioDetails.getIdSegmento()).get());
			pedido.setPrecioTotal(0.0);
	
			for (int i = 0; i < pedido.getDetallePedidos().size(); i++) {
				
				
				pedido.getDetallePedidos().get(i).setIsConfirmed(true);
				pedido.setPrecioTotal(pedido.getDetallePedidos().get(i).getPrecio() + pedido.getPrecioTotal());
				
			}
			pedido.setPrecioTotal(Math.round(pedido.getPrecioTotal()*100.0)/100.0);
			
			model.addAttribute("pedido", pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cart/cart";
	}
	@GetMapping("update")
	public String Update(@RequestParam("id") String id, @RequestParam("cant") Integer cant, Model model){
		

	try {
		
		Optional<DetallePedido> detalle = detalleService.findById(Integer.parseInt(id));
		detalle.get().setCantidad(cant);
		detalleService.update(detalle.get());
	
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	  return "redirect:/cart";
	} 
	
	
	@GetMapping("delete")
	public String Delete(@RequestParam("id") String id, Model model){
		
		
		
	try {
		
		detalleService.deleteById(Integer.parseInt(id));
		
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	  return "redirect:/cart";
	} 
	
	
}