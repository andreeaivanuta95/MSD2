package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.service.entities.Projects;

@Stateless @LocalBean
public class ProjectsDataServiceEJB implements ProjectsDataService {
	
	private static Logger logger = Logger.getLogger(ProjectsDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;		
	public ProjectsDataServiceEJB() {		
	}
	
	@PostConstruct
		public void init() {
			logger.info("POSTCONSTRUCT_INIT : "+this.em);
		}
		
	@Override
	public Projects addProject(Projects projectToAdd) {
		em.persist(projectToAdd);
		em.flush();
		em.refresh(projectToAdd);
		return projectToAdd;
		}
	
	@Override
	public Projects getProjectByProjectID(Integer projectID) {
		return em.find(Projects.class, projectID);
	}
	
	@Override
	public Collection<Projects> getProjects(){
		List<Projects> projects = em.createQuery("SELECT p FROM Projects p", Projects.class).getResultList();
		return projects;
	}
	
	@Override 
	public String removeProject(Projects projectToDelete) {
		projectToDelete = em.merge(projectToDelete);
		em.remove(projectToDelete);
		em.flush();
		return "True";
	}
	@Override 
	public Projects removeProjectByName(String projectName) {
		 Projects projectToRemove =  em.find(Projects.class, projectName);
		em.remove(projectToRemove);
		em.flush();
		return projectToRemove;
		}
	
	
	
	@Override
	public Projects getProjectByName(String projectName) {
		return em.createQuery("SELECT p FROM Projects p WHERE p.title = :projectName", Projects.class)
		 .setParameter("title", projectName)
		 .getSingleResult();	}
}
