package com.ma.pedidos.daoControllers.pedidos;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ma.pedidos.entities.PedidoCabecera;

public interface IPedidoDao extends CrudRepository<PedidoCabecera, Long>{
	List<PedidoCabecera> findByFecha(Date date);
}
