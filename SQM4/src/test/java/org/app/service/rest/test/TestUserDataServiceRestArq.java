package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

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

import javax.ws.rs.core.Response;

@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDataServiceRestArq {
	
	private static Logger logger = Logger.getLogger(TestUserDataServiceRestArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SQM4/rest/user";	
	
	@Deployment // Arquillian infrastructure
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SQM4-test.war")
				.addPackage(User.class.getPackage());
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
	//not working
	public void test2_DeleteUsers() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteUsers ...");
		Client client = ClientBuilder.newClient();
		Collection<User> users = client.target(resourceURL).request().get().readEntity(new GenericType<Collection<User>>(){});		
		
		for (User u : users) {
			client.target(resourceURL + u.getUserName()).request().delete();
		}
		
		Collection<User> usersAfterDelete = client.target(resourceURL).request().get().readEntity(new GenericType<Collection<User>>(){});
		assertTrue("Fail to read Users!", usersAfterDelete.size() == 0);
	}

	
	@Test
	public void test3_AddUsers() {
		logger.info("DEBUG: JUnit TESTING: test3_AddUsers ...");
		String resourceURL = serviceURL + "/add/";
		Client client = ClientBuilder.newClient();
		Response users;
	
		Integer usersAdd = 3;
		User user;
		for (int i=1; i <= usersAdd; i++){
			user = new User("utilizator" + i, "password", "utilizator" + i + "@gmail.com", "developer");
			users = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		}
	}
	
	@Test
	public void test4_GetUsers() {
		logger.info("DEBUG: JUnit TESTING: test5_GetUsers ...");
		Collection<User> users = ClientBuilder.newClient()
			.target(serviceURL)
			.request().get()
			.readEntity(new GenericType<Collection<User>>(){});
		
		assertTrue("Fail to read Users!", users.size() > 0);
		users.stream().forEach(System.out::println);
	}
	
	@Test
	public void test5_GetUserByUsername() {
		String resourceURL = serviceURL + "/raluca.chicos";
		logger.info("DEBUG: JUnit TESTING: test6_GetUserByUsername ...");
		User user = ClientBuilder.newClient().target(resourceURL)
			.request().accept(MediaType.APPLICATION_JSON)
			.get().readEntity(User.class);
		
		assertNotNull("REST Data Service failed!", user);
		logger.info(">>>>>> DEBUG: REST Response ..." + user);
	}
	

}
