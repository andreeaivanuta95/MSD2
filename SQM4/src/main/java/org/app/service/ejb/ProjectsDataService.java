package org.app.service.ejb;

import java.util.Collection;
import javax.ejb.Remote;

import org.app.service.entities.Projects;

@Remote
public interface ProjectsDataService {
	
	//create or update
	Projects addProject(Projects projectToAdd);
	
	// delete
	String removeProject(Projects projectToDelete);
	
	//read single project
	Projects getProjectById(Integer idProject);
	
	//read all projects
	Collection<Projects> getProjects();
	
	//custom read
	Projects getProjectByTitle(String title);	
	
	//Others
	String sayRest();
}