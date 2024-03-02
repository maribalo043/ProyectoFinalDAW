package com.mario.proyect.equipo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.partido.Partido;

public interface EquipoDAO extends CrudRepository<Equipo,Long>{

    @Query(value = "SELECT * FROM equipos ORDER BY categoria_id", nativeQuery = true)
    List<Equipo> findAllByCategoria();

    @Query(value = "SELECT * FROM equipos WHERE id != :equipoId", nativeQuery = true)
    List<Equipo> obtenerEquiposExceptoPorId(@Param("equipoId") long equipoId);
    
}
