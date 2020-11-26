package pe.edu.upc.techlive.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.Cliente;
import pe.edu.upc.techlive.models.services.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping
	public String inicio(Model model) {
		Cliente cliente = new Cliente();
		
		try {
			List<Cliente> clientes = clienteService.findAll();
			model.addAttribute("clientes", clientes);
			model.addAttribute("cliente", cliente);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "clientes/inicio";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("cliente") Cliente cliente) {
		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/clientes";
	}
	
	@GetMapping("/{id}/p")
	public String view(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Cliente> optional = clienteService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("cliente", optional.get());
				return "clientes/view";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/clientes";
	}
	
	@GetMapping("search")
	public String search() {
		return "clientes/search";
	}
}
