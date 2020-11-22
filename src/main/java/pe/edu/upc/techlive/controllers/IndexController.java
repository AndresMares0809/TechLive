package pe.edu.upc.techlive.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.techlive.models.entities.Producto;

@Controller
@RequestMapping("/")
@SessionAttributes("producto")
public class IndexController {
	// GET y POST
	@GetMapping
	public String index(Model model) {
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		return "index";
	}
	

}
