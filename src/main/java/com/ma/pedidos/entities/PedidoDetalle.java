package com.ma.pedidos.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="pedidos_detalle")
public class PedidoDetalle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="producto_id", nullable = false)
	private Producto producto;

	@Column(name="cantidad")
	private Float cantidad;
	
	@Column(name="precio_unitario")
	private Float precioUnitario;
	
	public PedidoDetalle() {}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
		if(this.cantidad != null)
			this.precioUnitario = cantidad * producto.getPrecioUnitario();
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
		if(this.producto != null)
			this.precioUnitario = cantidad * this.producto.getPrecioUnitario();
	}

	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	public Long getId() {
		return id;
	}

}
