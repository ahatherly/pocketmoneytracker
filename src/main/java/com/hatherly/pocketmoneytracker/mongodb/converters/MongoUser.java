package com.hatherly.pocketmoneytracker.mongodb.converters;

import org.bson.Document;

import com.hatherly.pocketmoneytracker.model.User;

public class MongoUser extends Document {
	private User user = null;
	
	public MongoUser(User user) {
		this.user = user;
		super.append("name", user.getName());
	}
	
	public User user() {
		User user = new User();
		user.setName(super.getString("name"));
		user.setId(super.getString("_id"));
		return user;
	}
}
