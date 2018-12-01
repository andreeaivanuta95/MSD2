package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Bugs;

@Remote    
public interface BugsDataService {
	
	//create or update
	Bugs addBug(Bugs bugToAdd);
	
	//delete
	String removeBug(Bugs bugToRemove);
	
	//read
	Bugs getBugById(Integer idBug);
	Collection<Bugs> getBugs();
	
	Bugs getBugByTitle(String title);
	
	//Others
	String sayRest();
}
