package es.uniovi.asw.dbManagement.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.integration.leader.Candidate;
import es.uniovi.asw.dbManagement.model.Candidature;

public interface CandidatureRepository extends CrudRepository<Candidature, Long>{
	
	List<Candidate> findByElectionsId(Long long1);
}
