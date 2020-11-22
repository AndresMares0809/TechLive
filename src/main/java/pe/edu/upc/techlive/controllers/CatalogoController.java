package pe.edu.upc.techlive.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.Producto;
import pe.edu.upc.techlive.models.services.ProductoService;



@Controller
@RequestMapping("/catalog")
public class CatalogoController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public String inicio(Model model) {
	
		try {
			List<Producto> productos = productoService.findAll();
			model.addAttribute("productos", productos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "catalog/catalog-view";
	}

}