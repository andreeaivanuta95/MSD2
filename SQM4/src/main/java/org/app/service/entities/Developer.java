package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Developer extends User implements Serializable{
	
	@OneToMany(cascade = ALL)
	private List<Bugs> bugs = new ArrayList<>();

	public Developer(String userName, String password, String email, String role, List<Bugs> bugs) {
		super(userName, password, email, role);
		this.bugs = bugs;
	}

	public Developer() {
		super();
	}

	public Developer(String userName, String password, String email, String role) {
		super(userName, password, email, role);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bugs == null) ? 0 : bugs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Developer other = (Developer) obj;
		if (bugs == null) {
			if (other.bugs != null)
				return false;
		} else if (!bugs.equals(other.bugs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Developer [bugs=" + bugs + "]";
	}
	
	
	
}
