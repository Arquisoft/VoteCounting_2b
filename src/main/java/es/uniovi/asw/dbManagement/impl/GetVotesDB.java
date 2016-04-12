package es.uniovi.asw.dbManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.dbManagement.GetVotes;
import es.uniovi.asw.dbManagement.model.Election;


public class GetVotesDB implements GetVotes
{

	@Override
	public Election getVotes(String electionName) {
		return null;
	}	
	/*
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
	*/
}