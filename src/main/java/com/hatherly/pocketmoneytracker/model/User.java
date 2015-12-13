package com.hatherly.pocketmoneytracker.model;

import com.google.gson.Gson;

public class User {
	private String Id;
	private String Name;
	
	public String toJSON() {
		// Serialise to JSON
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
