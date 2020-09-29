package com.ma.pedidos.daoControllers.productos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ma.pedidos.entities.Producto;

@Service
public class ProductosService implements IProductosService{

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public List<Producto> getProductos() {
		return (List<Producto>) productoDao.findAll();
	}
	
	@Override
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}
	
	@Override
	public Producto update(Long id, Producto producto) {
		producto.setId(id);
		return productoDao.save(producto);
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return productoDao.findById(id);
	}
	
	@Override
	public void deleteById(Long id) {
		this.productoDao.deleteById(id);
	}

}
