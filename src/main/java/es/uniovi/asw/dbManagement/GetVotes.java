package es.uniovi.asw.dbManagement;

import es.uniovi.asw.dbManagement.model.Election;


public interface GetVotes
{
	public Election getVotes(String electionName);
}