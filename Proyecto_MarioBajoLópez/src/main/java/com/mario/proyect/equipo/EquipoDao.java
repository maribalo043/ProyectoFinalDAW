package com.mario.proyect.equipo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.partido.Partido;

public interface EquipoDAO extends CrudRepository<Equipo,Long>{

    @Query(value = "SELECT * FROM equipos ORDER BY categoria_id", nativeQuery = true)
    List<Equipo> findAllByCategoria();

    @Query(value = "SELECT * FROM equipos e WHERE e.id <> :idEquipo AND e.id NOT IN (SELECT p.equipo_Local FROM partidos p WHERE p.equipo_Local = :idEquipo UNION SELECT p.equipo_Visitante FROM partidos p WHERE p.equipo_Visitante = :idEquipo)", nativeQuery = true)
    List<Equipo> obtenerEquiposNoEnlazadosConId(@Param("idEquipo") long idEquipo);

}
