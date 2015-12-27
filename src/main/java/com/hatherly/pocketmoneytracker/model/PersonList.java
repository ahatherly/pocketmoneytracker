package com.hatherly.pocketmoneytracker.model;

import java.util.List;

public class PersonList {
	List<Person> people = null;

	public PersonList(List<Person> people) {
		super();
		this.people = people;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
}
