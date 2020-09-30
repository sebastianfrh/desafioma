package com.ma.pedidos.restControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.daoControllers.pedidos.IPedidosService;
import com.ma.pedidos.daoControllers.productos.IProductosService;
import com.ma.pedidos.entities.PedidoCabecera;
import com.ma.pedidos.entities.Producto;
import com.ma.pedidos.error.RestErrors;

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
	
	@GetMapping
	public ResponseEntity<?> getPedidosByDate(@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
		return ResponseEntity.ok().body(this.pedidosService.findByDate(date));
	}
	
	@PostMapping
	public ResponseEntity<?> createPedido(@Validated @RequestBody PedidoCabecera pedido){
		ArrayList<String> noEncontrados = new ArrayList<>();
		if(pedido.getFecha() == null)
			pedido.setFecha(new Date());
		if(pedido.getHora() == null)
			pedido.setHora(new Date());
		
		pedido.getDetalle().forEach(det -> {
			Optional<Producto> prod = this.productosService.findById(det.getProductoLite());
			if(prod.isPresent()) 
				det.setProducto(prod.get());
			else
				noEncontrados.add(det.getProductoLite().toString());
		});
		
		if(noEncontrados.isEmpty())
			return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.save(pedido));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrors(String.format("Productos no encontrados: %s", String.join(",", noEncontrados))));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ArrayList<RestErrors> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ArrayList<RestErrors> errores = new ArrayList<>();
        for(ObjectError error:ex.getBindingResult().getAllErrors()) {
        	errores.add(new RestErrors(((FieldError) error).getField() +" "+ error.getDefaultMessage()));
        }
        return errores;
    }
}
