package com.hatherly.pocketmoneytracker.mongodb;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.hatherly.pocketmoneytracker.model.Person;
import com.hatherly.pocketmoneytracker.model.Transaction;
import com.hatherly.pocketmoneytracker.model.TransactionList;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoPerson;
import com.hatherly.pocketmoneytracker.mongodb.converters.MongoTransaction;
import com.mongodb.DBCursor;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
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
	
	public static Transaction getTransaction(String id) {
		Document result = (Document)Mongo.transactions().find(eq("_id", id)).first();
		if (result != null) {
			return MongoTransaction.transaction(result);
		} else {
			return null;
		}
	}
	
	public static void deleteTransaction(String id) {
		Mongo.transactions().deleteOne(eq("_id", id));
	}
	
	public static void deleteAllTransactionForPerson(String person_id) {
		Mongo.transactions().deleteMany(eq("personID", person_id));
	}

	public static TransactionList getTransactions(String personID, int offset, int count) {
		ArrayList transactions = new ArrayList();
		
		int transactionCount = getTransactionCount(personID);
		
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
		return new TransactionList(transactions, transactionCount, offset, personID);
	}
	
	private static int getTransactionCount(String person_id) {
		ArrayList stages = new ArrayList();
		
		stages.add(new Document("$group", new Document("_id", "$personID")
											.append("count", new Document("$sum", 1))));
		
		MongoCursor<Document> iterable = Mongo.transactions().aggregate(stages).iterator();
		
		while(iterable.hasNext()) {
			Document d = iterable.next();
			if (d.getString("_id").equals(person_id)) {
				return d.getInteger("count");
			}
		}
		return 0;
	}
}
