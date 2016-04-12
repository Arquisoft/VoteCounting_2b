package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionRepository extends CrudRepository<Election, Long>{

}
