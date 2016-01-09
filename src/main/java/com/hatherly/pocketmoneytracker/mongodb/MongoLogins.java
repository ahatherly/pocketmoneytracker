package com.hatherly.pocketmoneytracker.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.hatherly.pocketmoneytracker.model.Login;
import com.hatherly.pocketmoneytracker.model.LoginList;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;


public class MongoLogins {
	public static void addLogin(Login login) {
		Document user = new Document();
		user.put("_id", login.getLogin());
		user.put("digest", login.getDigest());
		user.put("salt", login.getSalt());
		user.put("person_id", login.getPersonID());
		
		// MongoDB Objects required to make this an upsert
		Bson filter = Filters.eq("_id", login.getLogin());
		UpdateOptions options = new UpdateOptions().upsert(true);
		Bson update =  new Document("$set", user);
		// And do the upsert
		Mongo.logins().updateOne(filter, update, options);
	}
	
	public static Login getLogin(String username) {
		Document result = (Document)Mongo.logins().find(eq("_id", username)).first();
		if (result != null) {
			Login login = new Login();
			login.setLogin(username);
			login.setDigest((String)result.get("digest"));
			login.setSalt((String)result.get("salt"));
			login.setPersonID((String)result.get("person_id"));
			return login;
		} else {
			return null;
		}
	}
	
	public static LoginList getLogins(String current_user, boolean is_admin) {
		ArrayList<Login> logins = new ArrayList<Login>();
		MongoCursor<Document> cursor = Mongo.logins().find().iterator();
		try {
		    while (cursor.hasNext()) {
		    	Document result = cursor.next();
		    	Login login = new Login();
				login.setLogin((String)result.get("_id"));
				login.setDigest((String)result.get("digest"));
				login.setSalt((String)result.get("salt"));
				login.setPersonID((String)result.get("person_id"));
				System.out.println("User is admin? "+is_admin+" - adding login: "+login.getLogin());
		    	if (is_admin) {
		    		// If an admin, show all users
		    		logins.add(login);
		    	} else {
		    		// Is not an admin, just show the logged in user
		    		if (login.getLogin().equals(current_user)) {
		    			logins.add(login);
		    		}
		    	}
		    }
		} finally {
		    cursor.close();
		}
		return new LoginList(logins);
	}

}
