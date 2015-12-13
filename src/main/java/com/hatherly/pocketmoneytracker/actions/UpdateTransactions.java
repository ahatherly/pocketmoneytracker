package com.hatherly.pocketmoneytracker.actions;

import java.util.Date;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.Transaction;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;
import com.hatherly.pocketmoneytracker.mongodb.MongoTransactions;

public class UpdateTransactions {
	
	/**
	 * Add a new transaction and update the balance accordingly
	 * @param name
	 * @param amount
	 * @return
	 */
	public static double addTransaction(Person p, String name, double amount, String category) {
		Date date = new Date();
		
		// Add the transaction
		Transaction t = new Transaction(p.getId(), date, amount, name, category);
		MongoTransactions.updateTransaction(t);
		
		// And update the balance
		double balance = p.getBalance() + amount;
		p.setBalance(balance);
		MongoPeople.updatePerson(p);
		
		return balance;
	}

}
