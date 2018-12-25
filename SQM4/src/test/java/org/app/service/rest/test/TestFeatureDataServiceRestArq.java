package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.app.service.entities.Bugs;
import org.app.service.entities.Features;
import org.app.service.entities.Projects;
import org.app.service.entities.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFeatureDataServiceRestArq {

	private static Logger logger = Logger.getLogger(TestFeatureDataServiceRestArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SQM4/rest/feature";	
	
	@Deployment // Arquillian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SQM4-test.war")
				.addPackage(Features.class.getPackage());
	}
	
	@Test
	public void test1_GetMessage() {
		String resourceURL = serviceURL + "/test";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		String response = ClientBuilder.newClient().target(resourceURL).request().get().readEntity(String.class);
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test2_AddFeatures() {
		// addIntoCollection
		logger.info("DEBUG: JUnit TESTING: test2_AddFeatures ...");
		String resourceURL = serviceURL + "/add/";
		Client client = ClientBuilder.newClient();
		Response features;
		
		Integer featuresAdd = 3;
		Features feature;
		for (int i=1; i <= featuresAdd; i++){
			feature = new Features(null, "titlu" + i, null, null, null);
			features = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(feature, MediaType.APPLICATION_JSON));
		}
	}
	
	@Test
	public void test3_GetFeatures() {
		logger.info("DEBUG: JUnit TESTING: test3_GetFeatures ...");
		Collection<Features> features = ClientBuilder.newClient()
			.target(serviceURL)
			.request().get()
			.readEntity(new GenericType<Collection<Features>>(){});
		
		assertTrue("Fail to read Features!", features.size() > 0);
		features.stream().forEach(System.out::println);
	}
	
	@Test
	public void test4_GetFeatureByTitle() {
		String resourceURL = serviceURL + "/titluCautat";
		logger.info("DEBUG: JUnit TESTING: test4_GetFeatureByTitle ...");
		Features feature = ClientBuilder.newClient().target(resourceURL)
			.request().accept(MediaType.APPLICATION_JSON)
			.get().readEntity(Features.class);
		
		assertNotNull("REST Data Service failed!", feature);
		logger.info(">>>>>> DEBUG: REST Response ..." + feature);
	}
	
	@Test
	public void test5_DeleteFeatures() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test5_DeleteFeatures ...");
		Client client = ClientBuilder.newClient();
		Collection<Features> features = client.target(serviceURL).request().get().readEntity(new GenericType<Collection<Features>>(){});		
		
		for (Features f : features) {
			client.target(resourceURL + f.getIdFeature()).request().delete();
		}
		
		Collection<Features> featuressAfterDelete = client.target(serviceURL).request().get().readEntity(new GenericType<Collection<Features>>(){});
		assertTrue("Fail to read Bugs!", featuressAfterDelete.size() == 0);
	}
}
