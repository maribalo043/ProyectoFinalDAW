package com.spring.start.cliente;

import java.util.List;

import com.spring.start.agentecliente.AgenteCliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=3, max=30, message="El nombre no puede tener menos de 3 caracteres ni más de 30")
	private String nombre;
	
	@OneToMany(targetEntity=AgenteCliente.class, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<AgenteCliente> agenteCliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	public List<AgenteCliente> getAgenteCliente() {
		return agenteCliente;
	}

	public void setAgenteCliente(List<AgenteCliente> agenteCliente) {
		this.agenteCliente = agenteCliente;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
