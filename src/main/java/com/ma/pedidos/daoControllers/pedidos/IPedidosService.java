package com.ma.pedidos.daoControllers.pedidos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ma.pedidos.entities.PedidoCabecera;

public interface IPedidosService {
	
	public Optional<PedidoCabecera> findById(Long id);
	public List<PedidoCabecera> findByDate(Date date);
	public PedidoCabecera save(PedidoCabecera pedido);

}
