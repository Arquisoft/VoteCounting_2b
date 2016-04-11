package es.uniovi.asw.dbManagement.persistence;

import org.springframework.data.repository.CrudRepository;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionsRepository extends CrudRepository<Election, Long>
{
	public Election findByName(String name);
}