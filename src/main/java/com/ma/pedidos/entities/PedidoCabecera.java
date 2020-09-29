package com.ma.pedidos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="pedidos_cabecera")
public class PedidoCabecera implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="direccion", length=150)
	private String direccion;
	
	@Column(name="mail", length=60)
	private String mail;
	
	@Column(name="telefono", length=20)
	private String telefono;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fecha;
	
	@Temporal(TemporalType.TIME)
	@Column(name="horario")
	private Date hora;
	
	@Column(name="monto_total")
	private Float precioTotal;
	
	@Column(name="aplico_descuento")
	private Boolean descuento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
    private Estado estado;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="cabecera_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<PedidoDetalle> detalle;

	public List<PedidoDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<PedidoDetalle> detalle) {
		this.detalle = detalle;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Boolean getDescuento() {
		return descuento;
	}

	public void setDescuento(Boolean descuento) {
		this.descuento = descuento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}
	
	

}
