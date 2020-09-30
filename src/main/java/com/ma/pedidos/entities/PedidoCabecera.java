package com.ma.pedidos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="pedidos_cabecera")
public class PedidoCabecera implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name="direccion", length=150)
	private String direccion;
	
	@NotEmpty
	@Email
	@Column(name="mail", length=60)
	private String mail;
	
	@NotNull
	@NotEmpty
	@Column(name="telefono", length=20)
	private String telefono;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	
	@Temporal(TemporalType.TIME)
	@Column(name="horario")
	@JsonFormat(pattern="HH:mm")
	private Date hora;
	
	@Column(name="monto_total")
	private Float precioTotal;
	
	@Column(name="aplico_descuento")
	private Boolean descuento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
    private Estado estado;
	
	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="cabecera_id")
	private List<PedidoDetalle> detalle;
	
	@PrePersist
    private void prePersist(){
		if(this.precioTotal == null)
			this.precioTotal = this.calcularTotal();
		
		if(this.estado == null)
			this.estado = Estado.PENDIENTE;
	}
	
	private float calcularTotal() {
		float total = 0;
		this.descuento = this.aplicaDescuento();
		for(PedidoDetalle det:this.detalle) {
			total += det.getPrecioUnitario();
		}
		
		return (float) (this.descuento?(total*0.7):total);
	}
	
	private boolean aplicaDescuento() {
		float cantProductos = 0;
		for(PedidoDetalle det:this.detalle) {
			cantProductos += det.getCantidad();
		}

		return cantProductos > 3;
	}

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
