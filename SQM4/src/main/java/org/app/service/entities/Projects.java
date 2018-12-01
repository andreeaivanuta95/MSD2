package org.app.service.entities;

import java.io.Serializable;
import java.util.*;

import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name="projects") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Projects implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer idProject;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private String status;
	@ManyToOne
	private ProjectManager projectOwner;
	
	@OneToMany(cascade = ALL)
	private List<Features> features = new ArrayList<>();

	public Projects(Integer idProject, String title, String description, Date startDate, Date endDate, String status,
			ProjectManager projectOwner, List<Features> features) {
		super();
		this.idProject = idProject;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectOwner = projectOwner;
		this.features = features;
	}

	public Projects() {
		super();
		// TODO Auto-generated constructor stub
	}

	@XmlElement
	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}
	
	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@XmlElement
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@XmlElement
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement
	public ProjectManager getProjectOwner() {
		return projectOwner;
	}

	public void setProjectOwner(ProjectManager projectOwner) {
		this.projectOwner = projectOwner;
	}

	@XmlElementWrapper(name = "feature") @XmlElement(name = "feature")
	public List<Features> getFeatures() {
		return features;
	}

	public void setFeatures(List<Features> features) {
		this.features = features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		result = prime * result + ((projectOwner == null) ? 0 : projectOwner.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Projects other = (Projects) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (idProject == null) {
			if (other.idProject != null)
				return false;
		} else if (!idProject.equals(other.idProject))
			return false;
		if (projectOwner == null) {
			if (other.projectOwner != null)
				return false;
		} else if (!projectOwner.equals(other.projectOwner))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
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
		return "Projects [idProject=" + idProject + ", title=" + title + ", description=" + description + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + ", projectOwner=" + projectOwner
				+ ", features=" + features + "]";
	}
	
	//aici cred ca trebuie modificat la url
	public static String BASE_URL = "http://localhost:8080/SQM4/rest/projects/";
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		
		String restUrl = BASE_URL + this.getIdProject();
		return new AtomLink(restUrl, "get-id");
	}
	
	public void setLink(AtomLink link){
		
	}
}
