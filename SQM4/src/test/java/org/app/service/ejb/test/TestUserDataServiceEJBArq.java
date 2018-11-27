package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.UserDataService;
import org.app.service.ejb.UserDataServiceEJB;
import org.app.service.entities.User;
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
public class TestUserDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestUserDataServiceEJBArq.class.getName());

	@EJB
	private static UserDataService service;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(User.class.getPackage())
				.addClass(UserDataService.class)
				.addClass(UserDataServiceEJB.class)
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
	public void test2_AddUsers() {
		logger.info("DEBUG: JUnit TESTING: testAddUsers...");

		Integer numberOfUsersToAdd = 3;
		
		for (int i = 1; i <= numberOfUsersToAdd; i++) {
			service.addUser(new User("user" + i, "user" + i + "@sample.com", "parola", "Tester"));
		}
		Collection<User> users = service.getUsers();
		assertTrue("Failed to add users!", users.size() == numberOfUsersToAdd);
	}
	
	@Test
	public void test3_GetUsers() {
		logger.info("DEBUG: JUnit TESTING: testGetUsers...");
		Collection<User> users = service.getUsers();
		assertTrue("Failed to read Users", users.size() > 0);
	}
	
	@Test
	public void test4_DeleteUser() {
		logger.info("DEBUG: JUnit TESTING: testDeleteUser...");
		
		Collection<User> users = service.getUsers();
		
		for (User user : users) {
			service.removeUser(user);
		}
		Collection<User> usersAfterDelete = service.getUsers();
		assertTrue("Failed to read Users", usersAfterDelete.size() == 0);
	}
	
	
}
