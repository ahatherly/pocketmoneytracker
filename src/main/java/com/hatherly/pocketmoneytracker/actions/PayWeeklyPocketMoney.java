package com.hatherly.pocketmoneytracker.actions;

import java.util.Calendar;
import java.util.Date;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.Transaction;
import com.hatherly.pocketmoneytracker.mongodb.MongoPeople;
import com.hatherly.pocketmoneytracker.mongodb.MongoTransactions;

public class PayWeeklyPocketMoney {
	
	/**
	 * Check if a payment is due and pay money into each account that is due
	 */
	public static void payPocketMoney() {
		Calendar rightNow = Calendar.getInstance();
		int day = rightNow.get(Calendar.DAY_OF_WEEK);
		// Sunday = 1, Monday = 2, ... Saturday = 7
		
	}

}
