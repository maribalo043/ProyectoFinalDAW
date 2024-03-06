package com.spring.start.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.start.agente.Agente;

public interface ClienteDAO  extends CrudRepository<Cliente, Long>{

	@Query(value="SELECT * from cliente c where c.id NOT IN (SELECT e.cliente_id FROM agente_cliente e where e.agente_id = :id)",nativeQuery = true)
	List<Cliente> findNotLinkCliente(long id);
}
