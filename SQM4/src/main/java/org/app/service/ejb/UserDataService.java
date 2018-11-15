package org.app.service.ejb;

import java.util.Collection;
import javax.ejb.Remote;

import org.app.service.entities.User;

@Remote
public interface UserDataService{
	
	//create or update
	User addUser(User userToAdd);
	
	//delete
	String removeUser(User userToRemove);
	
	//read
	User getUserByUsername(String username);
	Collection<User> getUsers();
	
	//Custom Read:custom query
	User getUserByEmail(String email);
	
	//Others
	String sayRest();
}
