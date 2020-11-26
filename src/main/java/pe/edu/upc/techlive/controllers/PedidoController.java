package pe.edu.upc.techlive.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.techlive.models.entities.DetallePedido;
import pe.edu.upc.techlive.models.entities.Pedido;
import pe.edu.upc.techlive.models.services.DetallePedidoService;
import pe.edu.upc.techlive.models.services.PedidoService;
import pe.edu.upc.techlive.utils.EstadoPedido;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detalleService;

	@PostMapping("add")
	public String add(@ModelAttribute("pedido") Pedido pedido, Model model) {

		try {

			Date date = new Date(System.currentTimeMillis());
			pedido.setFechaPedido(date);
			pedido.setPrecioTotal(pedido.getPrecioTotal() + 15.00);
			pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
			pedidoService.save(pedido);
			
			for (DetallePedido detalle : pedido.getDetallePedidos()) {
				detalle.setPedido(pedido);
				detalle.setIsConfirmed(true);
				detalleService.update(detalle);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/catalog";
	}
}
