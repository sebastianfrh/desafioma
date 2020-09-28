package com.ma.pedidos.apiRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.daoControllers.IProductosService;
import com.ma.pedidos.entities.Producto;

@RestController
@RequestMapping (value="productos")
public class ProductosRestController {

	@Autowired
	private IProductosService productosService;

	@GetMapping(value="list")
	public List<Producto> getProductos() {
		return productosService.getProductos();
	}
	
}
