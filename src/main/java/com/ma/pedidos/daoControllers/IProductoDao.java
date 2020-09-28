package com.ma.pedidos.daoControllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ma.pedidos.entities.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{

}
