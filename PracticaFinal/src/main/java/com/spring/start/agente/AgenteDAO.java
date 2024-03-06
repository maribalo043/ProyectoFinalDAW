package com.spring.start.agente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface AgenteDAO extends CrudRepository<Agente, Long>{

	
}
