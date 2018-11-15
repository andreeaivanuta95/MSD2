package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.service.entities.User;


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
	@Override
	public User addUser(User userToAdd) {
		em.persist(userToAdd);
		em.flush();
		em.refresh(userToAdd);
		return userToAdd;
	}

	//read
	@Override
	public User getUserByUsername(String userName) {
		return em.find(User.class, userName);
	}

	public Collection<User> getUsers() {
		List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		return users;
	}

	//remove
	public String removeUser(User userToRemove) {
		userToRemove = em.merge(userToRemove);
		em.remove(userToRemove);
		em.flush();
		return "True";
	}

	//custom read
	@Override
	public User getUserByEmail(String email) {
		return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
				 .setParameter("email", email)
				 .getSingleResult();
	}

	//Others
	public String sayRest() {
		return "User service is on";
	}

}
