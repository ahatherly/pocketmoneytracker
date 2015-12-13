package com.hatherly.pocketmoneytracker.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoPerson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

public class Mongo {
	
	// Singleton
	private static Mongo _instance = new Mongo();
	
	private MongoDatabase mongoDatabase = null;
	
	private Mongo() {
		MongoClient mongoClient = new MongoClient();
		mongoDatabase = mongoClient.getDatabase("pocketmoneytracker");
	}
	public static MongoCollection people() {
		return _instance.mongoDatabase.getCollection("people");
	}
	public static MongoCollection transactions() {
		return _instance.mongoDatabase.getCollection("transactions");
	}
	public static MongoCollection logins() {
		return _instance.mongoDatabase.getCollection("logins");
	}
	public static MongoCollection users() {
		return _instance.mongoDatabase.getCollection("users");
	}
}
