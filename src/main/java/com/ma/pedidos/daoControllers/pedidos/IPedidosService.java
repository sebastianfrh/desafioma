package com.ma.pedidos.daoControllers.pedidos;

import java.util.Optional;

import com.ma.pedidos.entities.PedidoCabecera;

public interface IPedidosService {
	
	public Optional<PedidoCabecera> findById(Long id);
	public PedidoCabecera save(PedidoCabecera pedido);

}
