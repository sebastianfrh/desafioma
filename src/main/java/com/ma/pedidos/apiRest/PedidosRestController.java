package com.ma.pedidos.apiRest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.daoControllers.pedidos.IPedidosService;
import com.ma.pedidos.daoControllers.productos.IProductosService;
import com.ma.pedidos.entities.PedidoCabecera;

@RestController
@RequestMapping (value="pedidos")
public class PedidosRestController {

	@Autowired
	private IPedidosService pedidosService;
	@Autowired
	private IProductosService productosService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> getpedido(@PathVariable("id") Long id) {
		Optional<PedidoCabecera> ped = pedidosService.findById(id);
		if(ped.isPresent()) 
			 return ResponseEntity.ok(ped.get());
		else		
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	}
	
	@PostMapping
	public ResponseEntity<PedidoCabecera> createPedido(@Validated @RequestBody PedidoCabecera pedido){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.save(pedido));
	}
}
