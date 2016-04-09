package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vote {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	private Election election;
	@ManyToOne
	private PollingPlace pollingPlace;
	
	private int numVotes;
	
	public Vote() {}

	public int getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		if (election != null) {
			this.election._getVotes().remove(this);
			this.election = election;
			this.election._getVotes().add(this);
		}
	}

	public PollingPlace getPollingPlace() {
		return pollingPlace;
	}

	public void setPollingPlace(PollingPlace pollingPlace) {
		if (pollingPlace != null) {
			this.pollingPlace._getVotes().remove(this);
			this.pollingPlace = pollingPlace;
			this.pollingPlace._getVotes().add(this);
		}
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", election=" + election + ", pollingPlace=" + pollingPlace + ", numVotes=" + numVotes
				+ "]";
	}

}
