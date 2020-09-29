package com.ma.pedidos.daoControllers.productos;

import org.springframework.data.repository.CrudRepository;

import com.ma.pedidos.entities.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
