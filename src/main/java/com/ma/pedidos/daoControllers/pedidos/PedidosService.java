package com.ma.pedidos.daoControllers.pedidos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ma.pedidos.entities.PedidoCabecera;

@Service
public class PedidosService implements IPedidosService{
	
	@Autowired
	private IPedidoDao pedidoDao;
	
	@Override
	public Optional<PedidoCabecera> findById(Long id) {
		return pedidoDao.findById(id);
	}

	@Override
	public PedidoCabecera save(PedidoCabecera pedido) {
		return pedidoDao.save(pedido);
	}

	@Override
	public List<PedidoCabecera> findByDate(Date date) {
		return pedidoDao.findByFecha(date);
	}

}
