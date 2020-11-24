package pe.edu.upc.techlive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import pe.edu.upc.techlive.models.services.DetallePedidoService;

@Controller
@RequestMapping("/")
@SessionAttributes("{contador}")
public class IndexController {
	
	@Autowired
	DetallePedidoService detalleService;
	
	@GetMapping
	public String index(Model model) {
		
		try {
			Integer contador = detalleService.countByIsConfirmed(false);
			model.addAttribute("contador", contador);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@GetMapping("login")
	public String login(){
		return "login";
	}
	

}
