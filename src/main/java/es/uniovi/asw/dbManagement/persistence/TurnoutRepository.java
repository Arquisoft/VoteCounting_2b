package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Turnout;

public interface TurnoutRepository extends CrudRepository<Turnout, Long> {

}
