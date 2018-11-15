package org.app.service.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Bugs implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer idBug;
	private String title;
	private String description;
	private String priority;
	private String status;
	@Temporal(TemporalType.DATE)
	private Date reportsDate;
	@ManyToOne
	private Developer asignee;
	@ManyToOne
	private Tester reporter;
	@ManyToOne
	private Features fromFeature;
	public Bugs(Integer idBug, String title, String description, String priority, String status, Date reportsDate,
			Developer asignee, Tester reporter, Features fromFeature) {
		super();
		this.idBug = idBug;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.reportsDate = reportsDate;
		this.asignee = asignee;
		this.reporter = reporter;
		this.fromFeature = fromFeature;
	}
	public Bugs() {
		super();
	}
	public Integer getIdBug() {
		return idBug;
	}
	public void setIdBug(Integer idBug) {
		this.idBug = idBug;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReportsDate() {
		return reportsDate;
	}
	public void setReportsDate(Date reportsDate) {
		this.reportsDate = reportsDate;
	}
	public Developer getAsignee() {
		return asignee;
	}
	public void setAsignee(Developer asignee) {
		this.asignee = asignee;
	}
	public Tester getReporter() {
		return reporter;
	}
	public void setReporter(Tester reporter) {
		this.reporter = reporter;
	}
	public Features getFromFeature() {
		return fromFeature;
	}
	public void setFromFeature(Features fromFeature) {
		this.fromFeature = fromFeature;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignee == null) ? 0 : asignee.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fromFeature == null) ? 0 : fromFeature.hashCode());
		result = prime * result + ((idBug == null) ? 0 : idBug.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((reporter == null) ? 0 : reporter.hashCode());
		result = prime * result + ((reportsDate == null) ? 0 : reportsDate.hashCode());
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
		if (fromFeature == null) {
			if (other.fromFeature != null)
				return false;
		} else if (!fromFeature.equals(other.fromFeature))
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
		if (reportsDate == null) {
			if (other.reportsDate != null)
				return false;
		} else if (!reportsDate.equals(other.reportsDate))
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
	@Override
	public String toString() {
		return "Bugs [idBug=" + idBug + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", status=" + status + ", reportsDate=" + reportsDate + ", asignee=" + asignee + ", reporter="
				+ reporter + ", fromFeature=" + fromFeature + "]";
	}

	
	
}
