package com.hatherly.pocketmoneytracker.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Login {
	
	@Expose
	private String login;
	@Expose
	private String personID;
	
	private String digest;
	private String salt;
	
	public Login() {
	}
	public Login(String login, String digest, String salt, String personID) {
		this.login = login;
		this.digest = digest;
		this.salt = salt;
		this.personID = personID;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String toJSON() {
		// Serialise to JSON
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
}
