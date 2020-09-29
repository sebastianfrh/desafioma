package com.ma.pedidos.apiRest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.daoControllers.productos.IProductosService;
import com.ma.pedidos.entities.Producto;

@RestController
@RequestMapping (value="productos")
public class ProductosRestController {

	@Autowired
	private IProductosService productosService;

	@GetMapping(value="/list")
	public List<Producto> getProductos() {
		return productosService.getProductos();
	}
	
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
		return ResponseEntity.status(HttpStatus.CREATED).body(productosService.save(producto));
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateProducto(@PathVariable("id") Long id, @RequestBody Producto producto){
		Optional<Producto> prod = productosService.findById(id);
		if(prod.isPresent()) 
			return ResponseEntity.ok().body(productosService.update(id,producto));
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");		   
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> getProducto(@PathVariable("id") Long id) {
		Optional<Producto> prod = productosService.findById(id);
		if(prod.isPresent()) 
			 return ResponseEntity.ok(prod.get());
		else		
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		Optional<Producto> prod = productosService.findById(id);
		if(prod.isPresent()) {
			productosService.deleteById(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
		}
	}
	
}
