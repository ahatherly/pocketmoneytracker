package com.hatherly.pocketmoneytracker.mongodb;

import org.bson.Document;

import com.hatherly.pocketmoneytracker.model.Login;
import com.hatherly.pocketmoneytracker.model.User;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;


public class MongoLogins {
	public static void addLogin(Login login) {
		DBObject user = new BasicDBObject();
		user.put("_id", login.getLogin());
		user.put("digest", login.getDigest());
		user.put("salt", login.getSalt());
		Mongo.logins().insertOne(user);
		//Logger.trace("Added new login: " + login.getLogin());
	}
	
	public static Login getLogin(String username) {
		Document result = (Document)Mongo.logins().find(eq("_id", username)).first();
		if (result != null) {
			Login login = new Login();
			login.setLogin(username);
			login.setDigest((String)result.get("digest"));
			login.setSalt((String)result.get("salt"));
			return login;
		} else {
			return null;
		}
	}
	
	public static void addUser(User user, String login) {
		Document doc = new MongoUser(user);
		doc.put("_id", login);
		Mongo.users().insertOne(doc);
		//Logger.trace("Added new user - login: " + login);
	}
	
	public static User getUser(String username) {
		//BasicDBObject query = new BasicDBObject("_id", username);
		MongoUser result = (MongoUser)Mongo.users().find(eq("_id", username)).first();
		if (result != null) {
			return result.user();
		} else {
			return null;
		}
	}
}
