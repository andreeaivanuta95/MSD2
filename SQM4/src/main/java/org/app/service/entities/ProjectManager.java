package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity

public class ProjectManager extends User implements Serializable{
	
	@ManyToOne
	private Bugs commentBug;
	
	@OneToMany(cascade = ALL)
	private List<Bugs> bugs = new ArrayList<>();

	public Bugs getCommentBug() {
		return commentBug;
	}

	public void setCommentBug(Bugs commentBug) {
		this.commentBug = commentBug;
	}

	public List<Bugs> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bugs> bugs) {
		this.bugs = bugs;
	}


	public int size() {
		return bugs.size();
	}

	public boolean isEmpty() {
		return bugs.isEmpty();
	}

	public boolean contains(Object o) {
		return bugs.contains(o);
	}

	public Iterator<Bugs> iterator() {
		return bugs.iterator();
	}

	public Object[] toArray() {
		return bugs.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return bugs.toArray(a);
	}

	public boolean add(Bugs e) {
		return bugs.add(e);
	}

	public boolean remove(Object o) {
		return bugs.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return bugs.containsAll(c);
	}

	public boolean addAll(Collection<? extends Bugs> c) {
		return bugs.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Bugs> c) {
		return bugs.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return bugs.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return bugs.retainAll(c);
	}

	public void clear() {
		bugs.clear();
	}

	public boolean equals(Object o) {
		return bugs.equals(o);
	}

	public int hashCode() {
		return bugs.hashCode();
	}

	public Bugs get(int index) {
		return bugs.get(index);
	}

	public Bugs set(int index, Bugs element) {
		return bugs.set(index, element);
	}

	public void add(int index, Bugs element) {
		bugs.add(index, element);
	}


	public Bugs remove(int index) {
		return bugs.remove(index);
	}


	public int indexOf(Object o) {
		return bugs.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return bugs.lastIndexOf(o);
	}

	public ListIterator<Bugs> listIterator() {
		return bugs.listIterator();
	}

	public ListIterator<Bugs> listIterator(int index) {
		return bugs.listIterator(index);
	}

	public List<Bugs> subList(int fromIndex, int toIndex) {
		return bugs.subList(fromIndex, toIndex);
	}

	@Override
	public String toString() {
		return "ProjectManager [commentBug=" + commentBug + ", bugs=" + bugs + ", getCommentBug()=" + getCommentBug()
				+ ", getBugs()=" + getBugs() + ", size()=" + size() + ", isEmpty()=" + isEmpty() + ", iterator()="
				+ iterator() + ", toArray()=" + Arrays.toString(toArray()) + ", hashCode()=" + hashCode()
				+ ", listIterator()=" + listIterator() + ", toString()=" + super.toString() + ", getUserName()="
				+ getUserName() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getRole()="
				+ getRole() + ", getClass()=" + getClass() + "]";
	}

	public ProjectManager(String userName, String password, String email, String role, Bugs commentBug,
			List<Bugs> bugs) {
		super(userName, password, email, role);
		this.commentBug = commentBug;
		this.bugs = bugs;
	}

	public ProjectManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectManager(String userName, String password, String email, String role) {
		super(userName, password, email, role);
		// TODO Auto-generated constructor stub
	}


	

}