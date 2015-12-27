package com.hatherly.pocketmoneytracker.model;

import java.util.List;

public class TransactionList {
	List<Transaction> transactions = null;
	int count = 0;
	int offset = 0;
	String person_id = null;

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public TransactionList(List<Transaction> transactions, int count, int offset, String person_id) {
		super();
		this.count = count;
		this.offset = offset;
		this.transactions = transactions;
		this.person_id = person_id;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
