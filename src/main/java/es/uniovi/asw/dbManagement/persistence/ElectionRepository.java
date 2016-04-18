package es.uniovi.asw.dbManagement.persistence;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionRepository extends CrudRepository<Election, Long>
{	
	Election findById(Long id);
	Election findByName(String name);
	Election findByStartDate(Date startName);
	Election findByExpiryDate(Date expiryDate);
	
	Election findByNameAndStartDate(String name, Date startDate);
	Election findByNameAndStartDateAndExpiryDate(String name, Date startDate, Date expiryDay);
	
	Election findByStartDateAndExpiryDate(Date startDate, Date expiryDay);
	
	
	@Query("SELECT e FROM Election e where e.startDate < CURRENT_TIMESTAMP and e.expiryDate > CURRENT_TIMESTAMP")
	Election findActual();
}