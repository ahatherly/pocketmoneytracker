package com.hatherly.pocketmoneytracker.mongodb.converters;

import org.bson.Document;

import com.hatherly.pocketmoneytracker.model.Person;

public class MongoPerson extends Document {
	private Person person = null;
	
	public MongoPerson(Person person) {
		this.person = person;
		super.append("name", person.getName());
		super.append("balance", person.getBalance());
		super.append("weeklyPocketMoneyAmount", person.getWeeklyPocketMoneyAmount());
		super.append("dayOfWeekPocketMoneyPaid", person.getDayOfWeekPocketMoneyPaid());
		super.append("profilePictureBase64", person.getProfilePictureBase64());
	}
	
	public static Person person(Document d) {
		Person person = new Person(d.getString("name"),
								 d.getDouble("balance"),
								 d.getDouble("weeklyPocketMoneyAmount"),
								 d.getInteger("dayOfWeekPocketMoneyPaid"));
		person.setProfilePictureBase64(d.getString("profilePictureBase64"));
		person.setId(d.getString("_id"));
		return person;
	}
}
