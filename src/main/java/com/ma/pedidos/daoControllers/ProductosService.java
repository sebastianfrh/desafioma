package com.ma.pedidos.daoControllers;

import java.util.List;

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

}
