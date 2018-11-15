package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Remote;

import org.app.service.entities.Projects;

@Remote
public interface ProjectsService {
	
	//create or update
	Projects addProject(Projects projectToAdd);
	
	// delete
	String removeProject(Projects projectToDelete);
	
	Projects removeProjectByName(String projectName);
	
	//read single project
	Projects getProjectByProjectID(Integer projectID);
	
	//read all projects
	Collection<Projects> getProjects();
	
	//custom read
	Projects getProjectByName(String projectName);	
	}
