package pe.edu.upc.techlive.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping("Procesadores")
	public String index() {
		return "Procesadores";
	}
	
	@GetMapping("Historial")
	public String historial() {
		return "Historial";
	}
	
	@GetMapping("DetallePedido")
	public String DetPed() {
		return "DetallePedido";
	}
	
	
}
