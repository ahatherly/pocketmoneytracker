package com.hatherly.pocketmoneytracker.model;

import java.util.Date;
import java.util.UUID;

public class Transaction {
	private String id = null;
	private Date date = null;
	private double amount = 0;
	private String name = null;
	private String category = null;
	private String personID = null;
	
	public Transaction(String personID, Date date, double amount, String name, String category) {
		super();
		this.id = UUID.randomUUID().toString();
		this.personID = personID;
		this.date = date;
		this.amount = amount;
		this.name = name;
		this.category = category;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
}
