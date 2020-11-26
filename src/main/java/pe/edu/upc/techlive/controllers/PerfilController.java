package pe.edu.upc.techlive.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.Cliente;
import pe.edu.upc.techlive.models.entities.Vendedor;
import pe.edu.upc.techlive.security.UsuarioDetails;
import pe.edu.upc.techlive.models.services.ClienteService;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.models.services.VendedorService;
import pe.edu.upc.techlive.utils.Segmento;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private DetallePedidoService detalleService;
	
	@GetMapping
	public String perfil( Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
	
		try {
			if(usuarioDetails.getSegmento() == Segmento.CLIENTE) {
				Optional<Cliente> optional = clienteService.findById(usuarioDetails.getIdSegmento());
				if (optional.isPresent()) {
					model.addAttribute("segmento", "CLIENTE");
					model.addAttribute("cliente", optional.get());
				}
				Integer contador = detalleService.countByClienteAndIsConfirmed(usuarioDetails.getIdSegmento(), false);
				model.addAttribute("contador", contador);
			} 
			else if(usuarioDetails.getSegmento() == Segmento.VENDEDOR) {
				
				Optional<Vendedor> optional = vendedorService.findById(usuarioDetails.getIdSegmento());
				if (optional.isPresent()) {
					model.addAttribute("segmento", "VENDEDOR");
					model.addAttribute("vendedor", optional.get());
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return "perfil/perfil";
	}
}
