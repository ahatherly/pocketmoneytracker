package com.hatherly.pocketmoneytracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

public class Person {
	private String id = null;
	private String name = null;
	private double balance = 0;
	private double weeklyPocketMoneyAmount = 0;
	private int dayOfWeekPocketMoneyPaid = 0; // Mon = 0, Tue = 1, ... , Sun = 6
	private String profilePictureBase64 = null;
	
	/**
	 * Add a new person
	 * @param name
	 * @param balance
	 * @param weeklyPocketMoneyAmount
	 * @param dayOfWeekPocketMoneyPaid
	 */
	public Person(String name, double balance, double weeklyPocketMoneyAmount, int dayOfWeekPocketMoneyPaid) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.balance = balance;
		this.weeklyPocketMoneyAmount = weeklyPocketMoneyAmount;
		this.dayOfWeekPocketMoneyPaid = dayOfWeekPocketMoneyPaid;
	}
	
	public String toJSON() {
		// Serialise to JSON
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getWeeklyPocketMoneyAmount() {
		return weeklyPocketMoneyAmount;
	}
	public void setWeeklyPocketMoneyAmount(double weeklyPocketMoneyAmount) {
		this.weeklyPocketMoneyAmount = weeklyPocketMoneyAmount;
	}
	public int getDayOfWeekPocketMoneyPaid() {
		return dayOfWeekPocketMoneyPaid;
	}
	public void setDayOfWeekPocketMoneyPaid(int dayOfWeekPocketMoneyPaid) {
		this.dayOfWeekPocketMoneyPaid = dayOfWeekPocketMoneyPaid;
	}
	public String getProfilePictureBase64() {
		return profilePictureBase64;
	}
	public void setProfilePictureBase64(String profilePictureBase64) {
		this.profilePictureBase64 = profilePictureBase64;
	}
	
	
}
