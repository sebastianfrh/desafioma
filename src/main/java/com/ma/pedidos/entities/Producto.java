package com.ma.pedidos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nombre", length=80)
	private String nombre;
	@Column(name="descripcion_corta", length=150)
	private String descripcionCorta;
	@Column(name="descripcion_larga", length=300)
	private String descripcionLarga;
	@Column(name="precio_unitario")
	private Float precioUnitario;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	
	public Float getPrecioUnitario() {
		return precioUnitario;
	}
	
	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public Long getId() {
		return id;
	}
	

}
