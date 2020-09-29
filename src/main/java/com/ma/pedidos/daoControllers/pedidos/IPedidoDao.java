package com.ma.pedidos.daoControllers.pedidos;

import org.springframework.data.repository.CrudRepository;

import com.ma.pedidos.entities.PedidoCabecera;

public interface IPedidoDao extends CrudRepository<PedidoCabecera, Long>{

}
