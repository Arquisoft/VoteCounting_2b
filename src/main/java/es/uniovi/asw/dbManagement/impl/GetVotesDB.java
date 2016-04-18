package es.uniovi.asw.dbManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.dbManagement.GetVotes;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.ElectionRepository;


public class GetVotesDB implements GetVotes
{
//	private final ElectionRepository electionRepository;
//	
//	@Autowired
//	public GetVotesDB(ElectionRepository electionRepository)
//	{
//		this.electionRepository = electionRepository;
//	}
	
	
	@Override
	public Election getVotes(String electionName)
	{
		//return elections.findByName(electionName);
		return null;
	}
}