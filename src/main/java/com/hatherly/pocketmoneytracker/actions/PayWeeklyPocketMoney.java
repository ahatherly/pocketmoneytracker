package com.hatherly.pocketmoneytracker.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.PersonList;
import com.hatherly.pocketmoneytracker.model.Transaction;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;
import com.hatherly.pocketmoneytracker.mongodb.MongoTransactions;

public class PayWeeklyPocketMoney {
	
	/**
	 * Check if a payment is due today and pay money into each account that is due
	 */
	public static String payPocketMoney() {
		return payPocketMoneyForDay(new Date()); 
	}

	/**
	 * Check payments due on date specified
	 * @param date_to_check Date to check for pocket money being due
	 */
	private static String payPocketMoneyForDay(Date date_to_check) {
		String messages = "";
		int day = DateToCalendar(date_to_check).get(Calendar.DAY_OF_WEEK);
		String date_key = new SimpleDateFormat("yyyy-MM-dd").format(date_to_check);
		
		PersonList l = MongoPeople.getPeople();
		for (Person p : l.getPeople()) {
			if (p.getDayOfWeekPocketMoneyPaid() == day) {
				Transaction existing_txn = MongoTransactions.getTransaction(date_key);
				if (existing_txn == null) {
					// Pocket money for today hasn't been added yet, so add it now.
					Transaction t = new Transaction(p.getId(),
													date_to_check,
													p.getWeeklyPocketMoneyAmount(),
													"Weekly Pockey Money",
													"weekly");
					t.setId(date_key);
					UpdateTransactions.addTransaction(p, t);
					messages = messages + date_to_check + " - paid weekly pocket money of £"+
												p.getWeeklyPocketMoneyAmount()+" to "+
												p.getName() + "\n";
				}
			}
		}
		
		if (messages.length()==0) {
			messages = "No updates required.";
		}
		
		return messages;
	}
	
	/**
	 * Convert from date to calendar object
	 * @param date
	 * @return
	 */
	private static Calendar DateToCalendar(Date date){ 
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  return cal;
	}
}
