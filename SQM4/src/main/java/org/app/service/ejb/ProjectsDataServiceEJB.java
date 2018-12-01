package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Projects;

@Path("projects")
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
	@PUT @Path("/{idProject}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Projects addProject(Projects projectToAdd) {
		em.persist(projectToAdd);
		em.flush();
		em.refresh(projectToAdd);
		return projectToAdd;
		}
	

	@GET @Path("/{idProject}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Projects getProjectById(@PathParam("idProject") Integer idProject) {
		return em.find(Projects.class, idProject);
	}
	
	@Override
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Projects> getProjects(){
		List<Projects> projects = em.createQuery("SELECT p FROM Projects p", Projects.class).getResultList();
		return projects;
	}
	
	@Override 
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String removeProject(Projects projectToDelete) {
		projectToDelete = em.merge(projectToDelete);
		em.remove(projectToDelete);
		em.flush();
		return "True";
	}

	@Override
	@GET @Path("/{title}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Projects getProjectByTitle(@PathParam("title") String title) {
		return em.createQuery("SELECT p FROM Projects p WHERE p.title = :title", Projects.class)
		 .setParameter("title", title)
		 .getSingleResult();	
	}
	
	@Override
	public String sayRest() {
		return "The service for Projects is done";
	}
}