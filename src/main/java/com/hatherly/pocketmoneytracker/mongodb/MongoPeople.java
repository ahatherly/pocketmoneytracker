package com.hatherly.pocketmoneytracker.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.PersonList;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoPerson;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

public class MongoPeople {
	
	/**
	 * Put document.
	 *
	 * @param id the id
	 * @param document the document
	 */
	public static void updatePerson(Person person) {
		// Turn our patient object into a MongoDB BSON object
		Document doc = new MongoPerson(person);
		
		// MongoDB Objects required to make this an upsert
		Bson filter = Filters.eq("_id", person.getId());
		UpdateOptions options = new UpdateOptions().upsert(true);
		Bson update =  new Document("$set", doc);
		
		// And do the upsert
		Mongo.people().updateOne(filter, update, options);		
	}

	public static Person getPerson(String id) {
		Document result = (Document)Mongo.people().find(eq("_id", id)).first();
		if (result != null) {
			return MongoPerson.person(result);
		} else {
			return null;
		}
	}
	
	public static PersonList getPeople() {
		ArrayList<Person> people = new ArrayList<Person>();
		MongoCursor<Document> cursor = Mongo.people().find().iterator();
		try {
		    while (cursor.hasNext()) {
		        people.add(MongoPerson.person(cursor.next()));
		    }
		} finally {
		    cursor.close();
		}
		return new PersonList(people);
	}
}
