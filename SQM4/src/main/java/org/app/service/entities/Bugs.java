package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bugs {
	
	public Bugs() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue
	private Integer idBug;
	public Bugs(Integer idBug, String title, String description, String priority, String status, Developer asignee,
			Tester reporter) {
		super();
		this.idBug = idBug;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.asignee = asignee;
		this.reporter = reporter;
	}
	@Override
	public String toString() {
		return "Bugs [idBug=" + idBug + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", status=" + status + ", asignee=" + asignee + ", reporter=" + reporter + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignee == null) ? 0 : asignee.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idBug == null) ? 0 : idBug.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((reporter == null) ? 0 : reporter.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Bugs other = (Bugs) obj;
		if (asignee == null) {
			if (other.asignee != null)
				return false;
		} else if (!asignee.equals(other.asignee))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idBug == null) {
			if (other.idBug != null)
				return false;
		} else if (!idBug.equals(other.idBug))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (reporter == null) {
			if (other.reporter != null)
				return false;
		} else if (!reporter.equals(other.reporter))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	private String title;
	private String description;
	private String priority;
	private String status;
	private Developer asignee;
	private Tester reporter;
}
