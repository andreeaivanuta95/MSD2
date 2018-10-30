package org.app.service.entities;

import javax.persistence.Entity;

@Entity
public class Developer extends User{
	
	private String assignedBugs;

	public String getAssignedBugs() {
		return assignedBugs;
	}

	public void setAssignedBugs(String assignedBugs) {
		this.assignedBugs = assignedBugs;
	}

	public Developer(String userName, String password, String email, String role, String assignedBugs) {
		super(userName, password, email, role);
		this.assignedBugs = assignedBugs;
	}

	public Developer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Developer(String userName, String password, String email, String role) {
		super(userName, password, email, role);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assignedBugs == null) ? 0 : assignedBugs.hashCode());
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
		if (assignedBugs == null) {
			if (other.assignedBugs != null)
				return false;
		} else if (!assignedBugs.equals(other.assignedBugs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Developer [assignedBugs=" + assignedBugs + "]";
	}

}
