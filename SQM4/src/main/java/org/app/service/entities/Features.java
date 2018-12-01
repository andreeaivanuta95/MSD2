package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="feature") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Features implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer idFeature;
	private String title;
	@ManyToOne
	private ProjectManager featureOwner;
	@ManyToOne
	private Projects fromProject;
	@OneToMany(cascade = ALL)
	private List<Bugs> bugName;
	public Features(Integer idFeature, String title, ProjectManager featureOwner, Projects fromProject,
			List<Bugs> bugName) {
		super();
		this.idFeature = idFeature;
		this.title = title;
		this.featureOwner = featureOwner;
		this.fromProject = fromProject;
		this.bugName = bugName;
	}
	public Features() {
		super();
	}
	
	@XmlElement
	public Integer getIdFeature() {
		return idFeature;
	}
	
	public void setIdFeature(Integer idFeature) {
		this.idFeature = idFeature;
	}
	
	@XmlElement
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement
	public ProjectManager getFeatureOwner() {
		return featureOwner;
	}
	
	public void setFeatureOwner(ProjectManager featureOwner) {
		this.featureOwner = featureOwner;
	}
	
	@XmlElement
	public Projects getFromProject() {
		return fromProject;
	}
	
	public void setFromProject(Projects fromProject) {
		this.fromProject = fromProject;
	}
	
	@XmlElementWrapper(name = "bugs") @XmlElement(name = "bugs")
	public List<Bugs> getBugName() {
		return bugName;
	}
	
	public void setBugName(List<Bugs> bugName) {
		this.bugName = bugName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugName == null) ? 0 : bugName.hashCode());
		result = prime * result + ((featureOwner == null) ? 0 : featureOwner.hashCode());
		result = prime * result + ((fromProject == null) ? 0 : fromProject.hashCode());
		result = prime * result + ((idFeature == null) ? 0 : idFeature.hashCode());
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
		Features other = (Features) obj;
		if (bugName == null) {
			if (other.bugName != null)
				return false;
		} else if (!bugName.equals(other.bugName))
			return false;
		if (featureOwner == null) {
			if (other.featureOwner != null)
				return false;
		} else if (!featureOwner.equals(other.featureOwner))
			return false;
		if (fromProject == null) {
			if (other.fromProject != null)
				return false;
		} else if (!fromProject.equals(other.fromProject))
			return false;
		if (idFeature == null) {
			if (other.idFeature != null)
				return false;
		} else if (!idFeature.equals(other.idFeature))
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
		return "Features [idFeature=" + idFeature + ", title=" + title + ", featureOwner=" + featureOwner
				+ ", fromProject=" + fromProject + ", bugName=" + bugName + "]";
	}
	
	public static String BASE_URL = "http://localhost:8080/SQM4/rest/user/";
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		
		String restUrl = BASE_URL + this.getIdFeature();
		return new AtomLink(restUrl, "get-id");
	}
	
	public void setLink(AtomLink link){
		
	}
	
	
}
