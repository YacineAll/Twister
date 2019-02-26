package com.twister.DataBases;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	private static MongoClient mc;

	/**
	 * une nouvelle connexion pour mongo 
	 * 
	 * @return {@link MongoDatabase} une nouvelle connexin pour la base mongo 
	 */
	public static MongoDatabase getConnectionToMongoDataBase() {
		mc = MongoClients.create(DBStatic.MONGO_HOST);
		MongoDatabase db = mc.getDatabase(DBStatic.MONGO_DB);
		return db;
	}

	public static void closeConnection() {
		mc.close();
	}

}
