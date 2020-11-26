package pe.edu.upc.techlive.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.entities.Producto;
import pe.edu.upc.techlive.models.services.ClienteService;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.models.services.ProductoService;
import pe.edu.upc.techlive.security.UsuarioDetails;

@Controller
@RequestMapping("/products")
@SessionAttributes("{contador}")
public class ProductoController {
	
	
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private DetallePedidoService detalleService;
    
    @Autowired
    private ClienteService clienteService;
    

 
    @GetMapping("search")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			 List<Producto> productos = productoService.findByTag(keyword.toLowerCase());
			 model.addAttribute("productos", productos);
			 model.addAttribute("keyword", keyword);
			 Integer contador = detalleService.countByClienteAndIsConfirmed(usuarioDetails.getIdSegmento(), false);
				model.addAttribute("contador", contador);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return "productos/result-search";
    }
    
    @GetMapping("{tag}-{id}/p")
	public String view(@PathVariable("id") Integer id, Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			Optional<Producto> optional = productoService.findById(id);
			if(optional.isPresent()) {
				String[] especificaciones = optional.get().getEspecificaciones().split("-");
				model.addAttribute("especificaciones", especificaciones);
				model.addAttribute("producto", optional.get());
				DetallePedido detallePedido = new DetallePedido();
				detallePedido.setPrecio(optional.get().getPrecioVenta());
				detallePedido.setProducto(optional.get());
				detallePedido.setCantidad(1);
				Integer contador = detalleService.countByClienteAndIsConfirmed(usuarioDetails.getIdSegmento(), false);
				model.addAttribute("contador", contador);
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
    
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
    	
		
		try {
			detalle.setIsConfirmed(false);
			detalle.setCliente(clienteService.findById(usuarioDetails.getIdSegmento()).get());
			detalle.setPrecio(detalle.getProducto().getPrecioVenta() * detalle.getCantidad());
			detalleService.save(detalle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/catalog";
	}
    
   
     
    
    
}
