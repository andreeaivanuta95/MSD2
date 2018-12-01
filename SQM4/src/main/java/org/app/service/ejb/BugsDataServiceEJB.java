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

import org.app.service.entities.Bugs;


@Path("bugs")
@Stateless 
@LocalBean   
public class BugsDataServiceEJB implements BugsDataService{
	
	private static Logger logger = Logger.getLogger(BugsDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public BugsDataServiceEJB() {
		
	}
	
	@PostConstruct
	public void init() {
		logger.info("Post-construct init: " + this.em);
	}
	
	@Override
	@PUT @Path("/{idBug}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Bugs addBug(Bugs bugToAdd) {
		em.persist(bugToAdd);
		em.flush();
		em.refresh(bugToAdd);
		return bugToAdd;

	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public String removeBug(Bugs bugToRemove) {
		bugToRemove = em.merge(bugToRemove);
		em.remove(bugToRemove);
		em.flush();
		return "True";
	}
	
	@GET @Path("/{idBug}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override 
	public Bugs getBugById(@PathParam("idBug") Integer idBug) {
		return em.find(Bugs.class,idBug);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Bugs> getBugs() {
		List<Bugs> users = em.createQuery("SELECT b FROM Bugs b", Bugs.class).getResultList();
		return users;
	}

	@GET @Path("/{title}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Bugs getBugByTitle(@PathParam("title") String title) {
		return em.createQuery("SELECT b FROM Bugs b WHERE b.title = :title", Bugs.class)
				 .setParameter("title", title)
				 .getSingleResult();
	}


	@Override
	public String sayRest() {
		return "The service for bugs is done";
	}

}
