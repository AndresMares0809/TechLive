package pe.edu.upc.techlive.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.entities.Producto;
import pe.edu.upc.techlive.models.services.ProductoService;

@Controller
@RequestMapping("/products")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
     
    @GetMapping("search")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
       
		try {
			 List<Producto> productos = productoService.findByTag(keyword.toLowerCase());
			 model.addAttribute("productos", productos);
			 model.addAttribute("keyword", keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return "productos/result-search";
    }
    
    @GetMapping("{tag}-{id}/p")
	public String view(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Producto> optional = productoService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("producto", optional.get());
				DetallePedido detallePedido = new DetallePedido();
				detallePedido.setPrecio(optional.get().getPrecioVenta());
				detallePedido.setProducto(optional.get());
				detallePedido.setCantidad(1);;
				
				model.addAttribute("detallePedido", detallePedido);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productos/view-producto";
	}
    
    @PostMapping("add")
	public String add(@ModelAttribute("detallePedido") DetallePedido detalle,
			Model model) {
		System.out.println(detalle.getCantidad());
		System.out.println(detalle.getProducto().getPrecioVenta());
		System.out.println(detalle.getProducto().getCategoria().getDenominacion());
		return "redirect:/";
	}
    
   
     
    
    
}
