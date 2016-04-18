package es.uniovi.asw.dbManagement.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionRepository extends CrudRepository<Election, Long>
{	
	Election findById(Long id);
	
	Election findByName(String name);
	List<Election> findByStartDate(Date startName);
	List<Election> findByExpiryDate(Date expiryDate);
	
	List<Election> findByNameAndStartDate(String name, Date startDate);
	List<Election> findByNameAndStartDateAndExpiryDate(String name, Date startDate, Date expiryDay);
	
	List<Election> findByStartDateAndExpiryDate(Date startDate, Date expiryDay);
	
	
	@Query("SELECT e FROM Election e where e.startDate < CURRENT_TIMESTAMP and e.expiryDate > CURRENT_TIMESTAMP")
	Election findActual();
}