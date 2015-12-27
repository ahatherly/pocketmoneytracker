package com.hatherly.pocketmoneytracker.mongodb.converters;

import org.bson.Document;

import com.hatherly.pocketmoneytracker.model.Transaction;

public class MongoTransaction extends Document {
	private Transaction txn = null;
	
	public MongoTransaction(Transaction txn) {
		this.txn = txn;
		super.append("personID", txn.getPersonID());
		super.append("name", txn.getName());
		super.append("amount", txn.getAmount());
		super.append("date", txn.getDate());
		super.append("category", txn.getCategory());
	}
	
	public static Transaction transaction(Document d) {
		Transaction txn = new Transaction(d.getString("personID"),
										  d.getDate("date"),
										  d.getDouble("amount"),
										  d.getString("name"),
										  d.getString("category"));
		txn.setId(d.getString("_id"));
		return txn;
	}
}
