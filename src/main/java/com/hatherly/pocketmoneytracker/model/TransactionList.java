package com.hatherly.pocketmoneytracker.model;

import java.util.List;

public class TransactionList {
	List<Transaction> transactions = null;

	public TransactionList(List<Transaction> transactions) {
		super();
		this.transactions = transactions;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
