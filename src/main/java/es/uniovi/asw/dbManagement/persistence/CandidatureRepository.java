package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Candidature;

public interface CandidatureRepository extends CrudRepository<Candidature, Long>{

}
