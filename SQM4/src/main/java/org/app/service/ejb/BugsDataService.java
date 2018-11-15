package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Bugs;

@Remote
public interface BugsDataService {
	
	//create or update
	Bugs addBug(Bugs bugToAdd);
	Bugs updateBug(Bugs bugToUpdate);
	Bugs closeBug(Bugs bugToClose);	
	
	//delete
	Bugs deleteBug(Bugs bugToDelete);	
	//read
	Bugs getBugById(String bugId);
	Collection<Bugs> getBugs();
	
	Bugs getBugByTitle(String title);
	
	//Others
	String sayRest();
}
