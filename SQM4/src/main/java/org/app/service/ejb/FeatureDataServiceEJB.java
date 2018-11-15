package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Features;


@Stateless
@LocalBean
public class FeatureDataServiceEJB implements FeatureDataService {
	private static Logger logger = Logger.getLogger(FeatureDataServiceEJB.class.getName());

	@PersistenceContext(unitName = "MSD")
	private EntityManager em;


	@PostConstruct
	public void init() {
		logger.info("Post-construct init: " + this.em);
	}

	@Override
	public Features addFeature(Features FeatureToAdd) {
		em.persist(FeatureToAdd);
		em.flush();
		em.refresh(FeatureToAdd);
		return FeatureToAdd;

	}

	@Override
	public Features updateFeature(Features FeatureToUpdate) {
		em.persist(FeatureToUpdate);
		em.flush();
		em.refresh(FeatureToUpdate);
		return FeatureToUpdate;

	}

	@Override
	public Features closeFeature(Features FeatureToClose) {
		FeatureToClose = em.merge(FeatureToClose);
		em.close();
		em.flush();
		return FeatureToClose;
	}

	@Override
	public Features deleteFeature(Features FeatureToDelete) {
		FeatureToDelete = em.merge(FeatureToDelete);
		em.remove(FeatureToDelete);
		em.flush();
		return FeatureToDelete;
	}

	@Override
	public Features getFeatureById(String FeatureId) {
		return em.find(Features.class, FeatureId);
	}

	@Override
	public Collection<Features> getFeatures() {
		List<Features> users = em.createQuery("SELECT b FROM Features b", Features.class).getResultList();
		return users;
	}

	@Override
	public Features getFeatureByTitle(String title) {
		return em.createQuery("SELECT f FROM Features f WHERE b.title = :title", Features.class).setParameter("title", title)
				.getSingleResult();
	}

	@Override
	public String sayRest() {
		return "The service for Features is done";
	}

}
