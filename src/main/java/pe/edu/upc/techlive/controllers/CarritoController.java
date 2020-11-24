package pe.edu.upc.techlive.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.services.DetallePedidoService;



@Controller
@RequestMapping("/cart")
@SessionAttributes("{contador}")
public class CarritoController {
	
	@Autowired
	private DetallePedidoService detalleService;
	
	@GetMapping
	public String inicio(Model model) {
	
		try {
			List<DetallePedido> detalles = detalleService.findByIsConfirmedFalse();
			model.addAttribute("detalles", detalles);
			Integer contador = detalleService.countByIsConfirmed(false);
			model.addAttribute("contador", contador);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cart/cart";
	}
	
	@GetMapping("delete")
	public String YourActionName(@RequestParam("id") String id, Model model){
		
		
		
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