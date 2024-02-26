package com.mario.proyect.juega;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JuegaDAO extends CrudRepository<Juega,Long>{
    
    void deleteByPartidoIdIn(List<Long> partidoIds);

}
