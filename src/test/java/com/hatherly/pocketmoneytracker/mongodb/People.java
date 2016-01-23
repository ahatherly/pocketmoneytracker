package com.hatherly.pocketmoneytracker.mongodb;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.gson.Gson;
import com.hatherly.pocketmoneytracker.actions.UpdateTransactions;
import com.hatherly.pocketmoneytracker.api.SecurityUtils;
import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.PersonList;
import com.hatherly.pocketmoneytracker.model.TransactionList;

public class People {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		SecurityUtils loginManager = new SecurityUtils();
		
		// Create an admin user
		//loginManager.createUser("admin", "password", null);
		
		/*PersonList l = MongoPeople.getPeople();
		for (Person p: l.getPeople()) {
			if (p.getName().equals("Laura")) {
				loginManager.createOrUpdateUser("laura", "laura", p.getId());
				System.out.println("Added login for Laura");
			}
			if (p.getName().equals("Luke")) {
				loginManager.createOrUpdateUser("luke", "luke", p.getId());
				System.out.println("Added login for Luke");
			}
		}*/
		
		Person p = new Person("Laura Savings", 0, 0, 1);
		MongoPeople.updatePerson(p);
		p = new Person("Luke Savings", 0, 0, 1);
		MongoPeople.updatePerson(p);
		
		// Serialise to JSON
		Gson gson = new Gson();
				
		PersonList l = MongoPeople.getPeople();
		System.out.println(gson.toJson(l));
		
		//Person p = l.getPeople().get(0);
		//UpdateTransactions.addTransaction(p, "Opening Balance", 8.0, null);
		//UpdateTransactions.addTransaction(p, "Weekly pocket money", 1.0, "weekly");
		//UpdateTransactions.addTransaction(p, "Bad behaviour penalty", -0.5, "penalties");
		
		//Person p = l.getPeople().get(1);
		//TransactionList t = MongoTransactions.getTransactions(p.getId(), 0, 10);
		//System.out.println(gson.toJson(t));
		
	}
	
	

}
