package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Tester extends User implements Serializable{

	@OneToMany(cascade = ALL)
	private List<Bugs> bugs = new ArrayList<>();

	public Tester(String userName, String password, String email, String role, List<Bugs> bugs) {
		super(userName, password, email, role);
		this.bugs = bugs;
	}

	public Tester() {
		super();
	}

	public Tester(String userName, String password, String email, String role) {
		super(userName, password, email, role);
	
	}

	public List<Bugs> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bugs> bugs) {
		this.bugs = bugs;
	}
	
	

	
}
