package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Candidate {

	@Id @GeneratedValue
	private Long id;
	private String DNI;
	private String name;
	private String surname;
	
	@ManyToMany (fetch = FetchType.EAGER)
	private Set<OpenList> elections = new HashSet<>();
	@ManyToOne
	private Candidature candidature;
	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
	private Set<VoteOpenList> votes = new HashSet<>();
	
	public Candidate() {}
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<OpenList> getElections() {
		return new HashSet<>(elections);
	}
	
	Set<OpenList> _getElections() {
		return elections;
	}
	
	public void addElection(OpenList election) {
		if (election != null) {
			election._getCandidates().add(this);
			elections.add(election);
		}
	}
	
	public void removeElection(OpenList election) {
		if (election != null) {
			election._getCandidates().remove(this);
			elections.remove(election);
		}
	}

	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		if (this.candidature != null) {
			this.candidature._getCandidates().remove(this);
			this.candidature = candidature;
			this.candidature._getCandidates().add(this);
		}
	}

	public Set<VoteOpenList> getVotes() {
		return new HashSet<>(votes);
	}
	
	Set<VoteOpenList> _getVotes() {
		return votes;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
	
}
