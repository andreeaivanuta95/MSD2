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

import org.app.patterns.EntityRepository;
import org.app.service.entities.Bugs;
import org.app.service.entities.Projects;
import org.app.service.rest.ApplicationConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
public class TestProjectDataServiceRest {
	private static Logger logger = Logger.getLogger(TestProjectDataServiceRest.class.getName());

	private static String serviceURL = "http://localhost:8080/SQM4/rest/projects";		
	
	@Deployment // Arquillian infrastructure
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "SQM4-test.war")
	                .addPackage(Projects.class.getPackage()); // all mode by default
	}	
	
//	@Test
	public void test1_GetMessage() {
		String resourceURL = serviceURL + "/test";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		String response = ClientBuilder.newClient().target(resourceURL).request().get().readEntity(String.class);
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test2_AddProject() {
		// addIntoCollection
		logger.info("DEBUG: Junit TESTING: test2_AddProject ...");
		String resourceURL = serviceURL + "/add/";
		Client client = ClientBuilder.newClient();
		Response projects;
		
		Integer projectsToAdd = 3;
		Projects project;
		for (int i=1; i <= projectsToAdd; i++){
			project = new Projects(null,"p"+i, null, null, null, null,null,null);
			projects = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(project, MediaType.APPLICATION_JSON));
		}
	}
	
	@Test
	public void test3_GetProjects() {
		logger.info("DEBUG: Junit TESTING: test3_GetProjects ...");
		Collection<Projects> projects = ClientBuilder.newClient().target(serviceURL).request().get().readEntity(new GenericType<Collection<Projects>>(){});
		assertTrue("Fail to read Projects!", projects.size() > 0);
		projects.stream().forEach(System.out::println);
	}

	@Test
	public void test4_GetProjectByTitle() {
		String resourceURL = serviceURL + "/titluCautat";
		logger.info("DEBUG: JUnit TESTING: test4_GetProjectByTitle ...");
		Projects project = ClientBuilder.newClient().target(resourceURL)
			.request().accept(MediaType.APPLICATION_JSON)
			.get().readEntity(Projects.class);
		
		assertNotNull("REST Data Service failed!", project);
		logger.info(">>>>>> DEBUG: REST Response ..." + project);
	}
	
	@Test
	public void test5_DeleteProject() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test5_DeleteProject ...");
		Client client = ClientBuilder.newClient();
		Collection<Projects> projects = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Projects>>(){});		
		
		for (Projects p: projects) {
			client.target(resourceURL + p.getIdProject()).request().delete();
		}
		
		Collection<Projects> projectsAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Projects>>(){});	
		assertTrue("Fail to read Projects!", projectsAfterDelete.size() == 0);
	}
		
	

}
