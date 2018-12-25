package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import org.app.service.entities.Features;

@Path("feature")
@Stateless @LocalBean
public class FeatureDataServiceEJB implements FeatureDataService {
	private static Logger logger = Logger.getLogger(FeatureDataServiceEJB.class.getName());

	@PersistenceContext(unitName = "MSD")
	private EntityManager em;


	@PostConstruct
	public void init() {
		logger.info("Post-construct init: " + this.em);
	}
	
	@POST @Path("/add/")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Override
	public Features addFeature(Features FeatureToAdd) {
		em.persist(FeatureToAdd);
		em.flush();
		em.refresh(FeatureToAdd);
		return FeatureToAdd;

	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Features deleteFeature(Features FeatureToDelete) {
		FeatureToDelete = em.merge(FeatureToDelete);
		em.remove(FeatureToDelete);
		em.flush();
		return FeatureToDelete;
	}

	@Override
	public Features getFeatureById(@PathParam("idFeature") Integer idFeature) {
		return em.find(Features.class, idFeature);
	}

	@Override
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Features> getFeatures() {
		List<Features> users = em.createQuery("SELECT b FROM Features b", Features.class).getResultList();
		return users;
	}

	@Override
	@GET @Path("/{title}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Features getFeatureByTitle(@PathParam("title") String title) {
		return em.createQuery("SELECT f FROM Features f WHERE f.title = :title", Features.class).setParameter("title", title)
				.getSingleResult();
	}

	@Override
	public String sayRest() {
		return "The service for Features is done";
	}

}
