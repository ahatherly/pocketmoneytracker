package com.hatherly.pocketmoneytracker.mongodb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import com.hatherly.pocketmoneytracker.api.SecurityUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Mongo {
	
	// Singleton
	private static Mongo _instance = new Mongo();
	
	private MongoDatabase mongoDatabase = null;
	private Properties props = null;
	
	private Mongo() {
		
		this.props = new Properties();
		try {
			props.load(this.getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			System.err.println("Could not load mongodb properties (config file: config.properties)");
		}

		// Load properties for the data store here
		String host = this.props.getProperty("host");
		int port = Integer.parseInt(this.props.getProperty("port"));
		String dbName = this.props.getProperty("dbName");
		
		MongoClient mongoClient = new MongoClient(host, port);
		mongoDatabase = mongoClient.getDatabase(dbName);
	}
	public static MongoCollection people() {
		return _instance.mongoDatabase.getCollection("people");
	}
	public static MongoCollection images() {
		return _instance.mongoDatabase.getCollection("images");
	}
	public static MongoCollection transactions() {
		return _instance.mongoDatabase.getCollection("transactions");
	}
	public static MongoCollection logins() {
		return _instance.mongoDatabase.getCollection("logins");
	}
	
	public static boolean checkForFirstUseOfDatabase() {
		MongoIterable<String> collections = _instance.mongoDatabase.listCollectionNames();
		for (String collection: collections) {
			if (collection.equals("logins")) {
				// The database is already initialised
				return false;
			}
		}
		// This is a new database, so initialise it with an initial login entry
		SecurityUtils loginManager = new SecurityUtils();
		try {
			loginManager.createOrUpdateUser("admin", "password", null);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Unable to initialise database with an initial admin user");
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unable to initialise database with an initial admin user");
		}
		return true;
	}
}
