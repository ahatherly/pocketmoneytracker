package com.hatherly.pocketmoneytracker.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class LoginList {
	
	@Expose
	List<Login> logins = null;

	public LoginList(List<Login> logins) {
		super();
		this.logins = logins;
	}

	public List<Login> getlogins() {
		return logins;
	}

	public void setlogins(List<Login> logins) {
		this.logins = logins;
	}
	
}
