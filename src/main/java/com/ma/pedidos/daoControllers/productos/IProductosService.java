package com.ma.pedidos.daoControllers.productos;

import java.util.List;
import java.util.Optional;

import com.ma.pedidos.entities.Producto;

public interface IProductosService {
	
	public List<Producto> getProductos();
	public Producto save(Producto producto);
	public Producto update(Long id, Producto producto);
	public Optional<Producto> findById(Long id);
	public void deleteById(Long id);

}
