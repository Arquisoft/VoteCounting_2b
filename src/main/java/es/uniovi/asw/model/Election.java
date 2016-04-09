package es.uniovi.asw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Election {

	@Id @GeneratedValue
	private Long id;
	private String name;
	private Date startDate;
	private Date expiryDate;
	private String instructions;
	
	@OneToMany(mappedBy = "election", fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<>();
	@OneToMany(mappedBy = "election", fetch = FetchType.EAGER)
	private Set<Turnout> turnout = new HashSet<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Set<Vote> getVotes() {
		return new HashSet<>(votes);
	}
	
	Set<Vote> _getVotes() {
		return votes;
	}

	public Set<Turnout> getTurnout() {
		return new HashSet<>(turnout);
	}
	
	Set<Turnout> _getTurnout() {
		return turnout;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Election [id=" + id + ", name=" + name + ", startDate=" + startDate + ", expiryDate=" + expiryDate
				+ ", instructions=" + instructions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Election other = (Election) obj;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
}
