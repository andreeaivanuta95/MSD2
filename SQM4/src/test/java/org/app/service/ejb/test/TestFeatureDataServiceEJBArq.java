package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.FeatureDataService;
import org.app.service.ejb.FeatureDataServiceEJB;
import org.app.service.entities.Features;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFeatureDataServiceEJBArq {

	private static Logger logger = Logger.getLogger(TestFeatureDataServiceEJBArq.class.getName());
	
	@EJB
	private static FeatureDataService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Features.class.getPackage())
				.addClass(FeatureDataService.class)
				.addClass(FeatureDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: JUnit Testing: getMessage ...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test2_AddFeatures() {
		logger.info("DEBUG: JUnit TESTING: testAddFeatures...");

		Integer numberOfFeaturesToAdd = 3;
		
		for (int i = 1; i <= numberOfFeaturesToAdd; i++) {
			service.addFeature(new Features(null, "title" + i, null, null, null));
		}
		Collection<Features> features = service.getFeatures();
		assertTrue("Failed to add features!", features.size() == numberOfFeaturesToAdd);
	}
	
	@Test
	public void test3_GetFeatures() {
		logger.info("DEBUG: JUnit TESTING: testGetFeatures...");
		Collection<Features> features = service.getFeatures();
		assertTrue("Failed to read Features", features.size() > 0);
	}
	
	@Test
	public void test4_GetFeatureById() {

		logger.info("DEBUG: JUnit TESTING: testGetFeatureById");
		Features feature = service.getFeatureById(11);
		assertTrue(feature != null);
	}
	
	@Test
	public void test5_GetFeatureByTitle() {

		logger.info("DEBUG: JUnit TESTING: testGetUserByEmail");
		Features feature = service.getFeatureByTitle("titlu12");
		assertTrue(feature != null);
	}
	
	
	
	/*
	@Test
	public void test6_DeleteFeature() {
		logger.info("DEBUG: JUnit TESTING: testDeleteFeature...");
		
		Collection<Features> features = service.getFeatures();
		
		for (Features feature : features) {
			service.deleteFeature(feature);
		}
		Collection<Features> featuresAfterDelete = service.getFeatures();
		assertTrue("Failed to read Features", featuresAfterDelete.size() == 0);
	}*/
	
	
}
