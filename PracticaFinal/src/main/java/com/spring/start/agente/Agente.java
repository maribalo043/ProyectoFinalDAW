package com.spring.start.agente;

import java.util.List;

import com.spring.start.agentecliente.AgenteCliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Agente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z]{3,30}$", message="Solo letras por favor y entre 3 y 30 caracteres")
	private String nombre;
	
	@NotNull
	@Pattern(regexp="^(\\+34|0034|34)?[6|7|9][0-9]{8}$", message="Tiene que ser un número válido")
	private String numeroTelefono;
	
	@OneToMany(targetEntity=AgenteCliente.class, mappedBy = "agente", cascade = CascadeType.ALL)
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

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public List<AgenteCliente> getAgenteCliente() {
		return agenteCliente;
	}

	public void setAgenteCliente(List<AgenteCliente> agenteCliente) {
		this.agenteCliente = agenteCliente;
	}
	
	
	
	
}
