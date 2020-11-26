package pe.edu.upc.techlive.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.techlive.models.entities.Producto;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.models.services.ProductoService;
import pe.edu.upc.techlive.security.UsuarioDetails;



@Controller
@RequestMapping("/catalog")
@SessionAttributes("{contador}")
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