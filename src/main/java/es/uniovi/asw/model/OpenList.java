package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class OpenList extends Election {

	private int numChoices;
	
	@ManyToMany(mappedBy="elections", fetch = FetchType.EAGER)
	private Set<Candidate> candidates = new HashSet<>();

	public OpenList() { }
	
	public int getNumChoices() {
		return numChoices;
	}

	public void setNumChoices(int numChoices) {
		this.numChoices = numChoices;
	}

	public Set<Candidate> getCandidates() {
		return new HashSet<>(candidates);
	}
	
	Set<Candidate> _getCandidates() {
		return candidates;
	}
	
	public void addCandidate(Candidate candidate) {
		if (candidate != null) {
			candidate._getElections().add(this);
			candidates.add(candidate);
		}
	}
	
	public void removeCandidate(Candidate candidate) {
		if (candidate != null) {
			candidate._getElections().remove(this);
			candidates.remove(candidate);
		}
	}
	
}
