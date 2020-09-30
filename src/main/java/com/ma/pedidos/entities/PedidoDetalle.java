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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="pedidos_detalle")
public class PedidoDetalle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="producto_id", nullable=false)
	@JsonProperty(value="producto", access=Access.READ_ONLY)
	private Producto producto;
	
	@NotNull
	@Transient
	@JsonProperty(value="producto", access=Access.WRITE_ONLY)
	private Long productoLite;

	@NotNull
	@Column(name="cantidad", nullable=false)
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

	public Long getProductoLite() {
		return productoLite;
	}

	public void setProductoLite(Long productoLite) {
		this.productoLite = productoLite;
	}

}
