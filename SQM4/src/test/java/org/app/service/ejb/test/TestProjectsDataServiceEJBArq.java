package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import org.app.service.ejb.ProjectsDataService;
import org.app.service.ejb.ProjectsDataServiceEJB;
import org.app.service.entities.Features;
import org.app.service.entities.ProjectManager;
import org.app.service.entities.Projects;
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
public class TestProjectsDataServiceEJBArq {

	private static Logger logger = Logger.getLogger(TestProjectsDataServiceEJBArq.class.getName());
	
	@EJB
	private static ProjectsDataService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Projects.class.getPackage())
				.addClass(ProjectsDataService.class)
				.addClass(ProjectsDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_AddProjects() {
		logger.info("DEBUG: Junit TESTING: testAddProject ");
		Integer projectsToAdd = 3;
		for(int i=1; i<projectsToAdd; i++) {
			service.addProject(new Projects(null,"p"+i, null, null, null, null,null,null));
		}
		Collection<Projects> projects = service.getProjects();				
		assertTrue("Fail to read projects!", projects.size()==projectsToAdd );
	}
	
 @Test 
 public void test2_GetProjectByID() {
	 logger.info("DEBUG: Junit TESTING: testGetProjectById ");
		Integer id= 101;
	Projects project = service.getProjectByProjectID(id);
	assertTrue(project != null);
 }
	
 @Test 
 public void test3_GetProjectByName() {
	 logger.info("DEBUG: Junit TESTING: testGetProjectById ");
		String name = "p1";
	Projects project = service.getProjectByName(name);
	assertTrue(project != null);
 }
 
	@Test
	public void test4_GetAllProjects() {
	logger.info("DEBUG: Junit TESTING: testGetProjects ");
		Collection<Projects> projects = service.getProjects();
		assertTrue("Fail to read projects!", projects.size()>0 );
	}
	
	@Test
	public void test5_DeleteProjectByName() {
	logger.info("DEBUG: Junit TESTING: testGetProjects ");
	String name = "p1";
	Projects project = service.removeProjectByName(name);
	assertTrue(project != null);
	}

	@Test
	public void test6_DeleteAllProjects() {
		logger.info("DEBUG: Junit TESTING: testDeleteProject ");
		Collection<Projects> projects = service.getProjects();				
		for(Projects p: projects) {
			service.removeProject(p);
		}
		Collection<Projects> projectsAfterDelete = service.getProjects();				
		assertTrue("Fail to read projects!", projectsAfterDelete.size()==0 );
	}
}