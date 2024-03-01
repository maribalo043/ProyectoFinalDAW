package com.mario.proyect.partido;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mario.proyect.equipo.Equipo;

public interface PartidoDAO extends CrudRepository<Partido,PartidoKey>{

    List<Partido> findByEquipoLocalOrEquipoVisitante(Equipo equipoLocal, Equipo equipoVisitante);

    List<Partido> findByEquipoLocalInOrEquipoVisitanteIn(List<Equipo> equipos, List<Equipo> equipos2);
    
}
