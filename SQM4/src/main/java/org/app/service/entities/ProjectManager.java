package org.app.service.entities;

import javax.persistence.Entity;

@Entity
public class ProjectManager extends User{
	
	public ProjectManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String comment;

	@Override
	public String toString() {
		return "ProjectManager [comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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
		ProjectManager other = (ProjectManager) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		return true;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ProjectManager(String userName, String password, String email, String role, String comment) {
		super(userName, password, email, role);
		this.comment = comment;
	}

}
