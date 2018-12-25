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
public class TestBugsDataServiceRestArq {

private static Logger logger = Logger.getLogger(TestBugsDataServiceRestArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SQM4/rest/bugs";	
	
	@Deployment // Arquillian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SQM4-test.war")
				.addPackage(Bugs.class.getPackage());
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
	public void test2_AddBugs() {
		logger.info("DEBUG: JUnit TESTING: test2_AddBugs ...");
		String resourceURL = serviceURL + "/add/";
		Client client = ClientBuilder.newClient();
		Response bugs;
		
		Integer bugsToAdd = 3;
		Bugs bug;
		for (int i=1; i <= bugsToAdd; i++){
			bug = new Bugs(null, "titlu" + i, "descriere" + i,"P3","to-do", null, null, null, null);
			bugs = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bug, MediaType.APPLICATION_JSON));
		}
	}
	
	@Test
	public void test3_GetBugs() {
		logger.info("DEBUG: JUnit TESTING: test3_GetBugs ...");
		Collection<Bugs> bugs = ClientBuilder.newClient()
			.target(serviceURL)
			.request().get()
			.readEntity(new GenericType<Collection<Bugs>>(){});
		
		assertTrue("Fail to read Bugs!", bugs.size() > 0);
		bugs.stream().forEach(System.out::println);
	}
	
	@Test
	public void test4_GetBugById() {
		String resourceURL = serviceURL + "/1";
		logger.info("DEBUG: JUnit TESTING: test4_GetBugById ...");
		Bugs bug = ClientBuilder.newClient().target(resourceURL).request().accept(MediaType.APPLICATION_JSON).get().readEntity(Bugs.class);
		
		assertNotNull("REST Data Service failed!", bug);
		logger.info(">>>>>> DEBUG: REST Response ..." + bug);
	}

	@Test
	//not working
	public void test5_DeleteBugs() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test5_DeleteBugs ...");
		Client client = ClientBuilder.newClient();
		Collection<Bugs> bugs = client.target(serviceURL)
										  .request().get()
										  .readEntity(new GenericType<Collection<Bugs>>(){});		
		
		for (Bugs b : bugs) {
			client.target(resourceURL + b.getIdBug()).request().delete();
		}
		
		Collection<Bugs> bugsAfterDelete = client.target(serviceURL).request().get().readEntity(new GenericType<Collection<Bugs>>(){});
		assertTrue("Fail to read Bugs!", bugsAfterDelete.size() == 0);
	}
}
