package es.uniovi.asw.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VotesOpenList")
@DiscriminatorValue("voteOpenList")
public class VoteOpenList extends Vote {
	
	@ManyToOne
	private Candidate candidate;
	
	public VoteOpenList() {}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		if (candidate != null) {
			this.candidate._getVotes().remove(this);
			this.candidate = candidate;
			this.candidate._getVotes().add(this);
		}
	}

	@Override
	public String toString() {
		return "VoteOpenList [candidate=" + candidate + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteOpenList other = (VoteOpenList) obj;
		if (candidate == null) {
			if (other.candidate != null)
				return false;
		} else if (!candidate.equals(other.candidate))
			return false;
		return true;
	}

}
