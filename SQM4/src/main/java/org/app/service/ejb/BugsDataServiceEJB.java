package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.service.entities.Bugs;

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
	public Bugs addBug(Bugs bugToAdd) {
		em.persist(bugToAdd);
		em.flush();
		em.refresh(bugToAdd);
		return bugToAdd;

	}

	
	@Override
	public Bugs updateBug(Bugs bugToUpdate) {
		em.persist(bugToUpdate);
		em.flush();
		em.refresh(bugToUpdate);
		return bugToUpdate;

	}
	
	@Override
	public Bugs closeBug(Bugs bugToClose) {
		bugToClose = em.merge(bugToClose);
		em.close();
		em.flush();
		return bugToClose;
	}
	
	@Override
	public Bugs deleteBug(Bugs bugToDelete) {
		bugToDelete = em.merge(bugToDelete);
		em.remove(bugToDelete);
		em.flush();
		return bugToDelete;
	}
	@Override 
	public Bugs getBugById(String bugId) {
		return em.find(Bugs.class,bugId);
	}
	
	@Override
	public Collection<Bugs> getBugs() {
		List<Bugs> users = em.createQuery("SELECT b FROM Bugs b", Bugs.class).getResultList();
		return users;
	}

	@Override
	public Bugs getBugByTitle(String title) {
		return em.createQuery("SELECT b FROM Bugs b WHERE b.title = :title", Bugs.class)
				 .setParameter("title", title)
				 .getSingleResult();
	}


	@Override
	public String sayRest() {
		return "The service for bugs is done";
	}

}
