package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long>{

}