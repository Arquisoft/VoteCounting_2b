package es.uniovi.asw.dbManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.dbManagement.GetVotes;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.ElectionsRepository;


public class GetVotesDB implements GetVotes
{
	private final ElectionsRepository elections;
	
	
	@Autowired
	public GetVotesDB(ElectionsRepository elections)
	{
		this.elections = elections;
	}
	
	
	@Override
	public Election getVotes(String electionName)
	{
		return elections.findByName(electionName);
	}
}