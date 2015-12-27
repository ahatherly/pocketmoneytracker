package com.hatherly.pocketmoneytracker.model;

public class Login {
	private String login;
	private String digest;
	private String salt;
	
	public Login() {
	}
	public Login(String login, String digest, String salt) {
		this.login = login;
		this.digest = digest;
		this.salt = salt;
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
}
