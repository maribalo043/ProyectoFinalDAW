package com.mario.proyect.juega;

import com.mario.proyect.equipo.Equipo;
import com.mario.proyect.partido.Partido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "juega")
public class Juega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*Le tengo que poner persist por que si pongo ALL me vacia la base de datos, debido a que eliminaba todo tanto padres como hijos y solo quedaba con datos la tabla de categorias por que no es hija de nadie*/
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "partido_id")
    private Partido partido;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    private String fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

	public  String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha){
		this.fecha=fecha;
	}
}
