package com.mario.proyect.juega;

import com.mario.proyect.equipo.Equipo;
import com.mario.proyect.partido.Partido;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "juega")
public class Juega {

    @EmbeddedId
    private JuegaKey id;

    @ManyToOne
    @MapsId("equipoId")
    private Equipo equipo;

    @ManyToOne
    @MapsId("partidoId")
    private Partido partido;

	public JuegaKey getId() {
		return id;
	}

	public void setId(JuegaKey id) {
		this.id = id;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Override
	public String toString() {
		return "Juega [id=" + id + ", equipo=" + equipo + ", partido=" + partido + "]";
	}

    
}
