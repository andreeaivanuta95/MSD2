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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.app.service.entities.User;

@Path("user") 
@Stateless @LocalBean
public class UserDataServiceEJB implements UserDataService{

	private static Logger logger = Logger.getLogger(UserDataServiceEJB.class.getName());
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public UserDataServiceEJB() {
		
	}
	
	@PostConstruct
	public void init() {
		logger.info("Post-construct init: " + this.em);
	}
	
	//create or update
	@POST @Path("/add/")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public User addUser(User userToAdd) {
		em.persist(userToAdd);
		em.flush();
		em.refresh(userToAdd);
		return userToAdd;
	}
	
	//read
	@Override
	@GET @Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User getUserByUsername(@PathParam("id") String userName) {
		return em.find(User.class, userName);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<User> getUsers() {
		List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		return users;
	}

	//remove
	@DELETE 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String removeUser(User userToRemove) {
		userToRemove = em.merge(userToRemove);
		em.remove(userToRemove);
		em.flush();
		return "True";
	}

	//custom read
	@Override
	public User getUserByEmail(@PathParam("email") String email) {
		return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
				 .setParameter("email", email)
				 .getSingleResult();
	}

	//Others
	@GET @Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayRest() {
		return "User service is on";
	}

}
