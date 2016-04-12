package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Candidature;

public interface CandidateRepository extends CrudRepository<Candidature, Long>{
	Candidature findByName(String name);
}
