package com.hatherly.pocketmoneytracker.mongodb;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.Transaction;
import com.hatherly.pocketmoneytracker.model.TransactionList;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoPerson;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoTransaction;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

public class MongoTransactions {
	
	/**
	 * Put document.
	 *
	 * @param id the id
	 * @param document the document
	 */
	public static void updateTransaction(Transaction txn) {
		// Turn our patient object into a MongoDB BSON object
		Document doc = new MongoTransaction(txn);
		
		// MongoDB Objects required to make this an upsert
		Bson filter = Filters.eq("_id", txn.getId());
		UpdateOptions options = new UpdateOptions().upsert(true);
		Bson update =  new Document("$set", doc);
		
		// And do the upsert
		Mongo.transactions().updateOne(filter, update, options);		
	}

	public static TransactionList getTransactions(String personID, int offset, int count) {
		ArrayList transactions = new ArrayList();
		MongoCursor<Document> cursor = Mongo.transactions().find(eq("personID", personID))
																  .sort(descending("date"))
																  .skip(offset).limit(count)
																  .iterator();
		try {
		    while (cursor.hasNext()) {
		        transactions.add(MongoTransaction.transaction(cursor.next()));
		    }
		} finally {
		    cursor.close();
		}
		return new TransactionList(transactions);
	}
}
