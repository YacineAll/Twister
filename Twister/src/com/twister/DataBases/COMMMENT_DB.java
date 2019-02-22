package com.twister.DataBases;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.twister.tools.Tools;

public class COMMMENT_DB {

	public static boolean addComment(int idUser, String nom,String prenom, String comment) {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");
		org.bson.Document doc = new org.bson.Document();

		doc.append("author_id", idUser);
		doc.append("Name", nom);
		doc.append("Name", prenom);
		doc.append("Date", Tools.getDateAfterNHour(0));
		doc.append("comment", comment);
		col.insertOne(doc);
		MongoDB.closeConnection();
		
		return true;
	}
}
