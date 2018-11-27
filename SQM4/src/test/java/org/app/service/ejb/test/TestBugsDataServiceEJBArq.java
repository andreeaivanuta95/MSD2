package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.BugsDataService;
import org.app.service.ejb.BugsDataServiceEJB;
import org.app.service.entities.Bugs;
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
public class TestBugsDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestBugsDataServiceEJBArq.class.getName());

	@EJB
	private static BugsDataService service;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Bugs.class.getPackage())
				.addClass(BugsDataService.class)
				.addClass(BugsDataServiceEJB.class)
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
	public void test2_AddBug() {
		logger.info("DEBUG: JUnit Testing: testAddBugs");

		Integer numberOfBugsToAdd = 5;
		
		for (int i = 1; i <= numberOfBugsToAdd; i++) {
			service.addBug(new Bugs(null, "title" + i, "description" + i,"P1","open", null, null, null, null));
		}
		
		Collection<Bugs> bugs = service.getBugs();
		
		assertTrue("Failed to add Bugs!", bugs.size() == numberOfBugsToAdd);
	}
	
	@Test
	public void test3_GetAllBugs() {
		logger.info("DEBUG: JUnit Testing: testGetAllBugs ...");

		Collection<Bugs> bugs = service.getBugs();
		assertTrue("Failed to read bugs", bugs.size() > 0);
	}
	
	@Test
	public void test4_DeleteAllBugs() {
		logger.info("DEBUG: JUnit Testing: testRemoveBugs");
		
		Collection<Bugs> bugs = service.getBugs();
		
		for (Bugs bug : bugs) {
			service.removeBug(bug);
		}
		Collection<Bugs> bugsAfterDelete = service.getBugs();
		assertTrue("Failed to read bugs",bugsAfterDelete.size() == 0);
	}
}

