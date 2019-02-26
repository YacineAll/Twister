package com.twister.DataBases;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.twister.tools.DateTools;

public class COMMMENT_DB {

	private static int id = getLastId() + 1;

	public static void printMongoDB() {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		MongoCursor<Document> mCursur = col.find().iterator();

		while (mCursur.hasNext()) {
			Document document = (Document) mCursur.next();
			System.out.println(document);
		}

		MongoDB.closeConnection();
	}

	public static boolean addComment(int idUser, String nom, String prenom, String comment) {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		org.bson.Document doc = new org.bson.Document();

		doc.append("id", id++);
		doc.append("author_id", idUser);
		doc.append("nom", nom);
		doc.append("prenom", prenom);
		doc.append("date", DateTools.getDateAfterNHour(0));
		doc.append("comment", comment);

		col.insertOne(doc);

		MongoDB.closeConnection();

		return true;
	}

	public static boolean removeComment(int id_user, int idComment) {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		org.bson.Document d = new Document();
		d.append("id", idComment);
		try {
			if (col.find(d).first().getInteger("author_id") == id_user) {
				col.deleteMany(d);
				return true;
			}

		} finally {
			MongoDB.closeConnection();
		}

		return false;
	}

	public static List<JSONObject> getUserCommentsId_Author(int id_author) throws JSONException {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		org.bson.Document doc = new org.bson.Document();

		doc.append("author_id", id_author);
		List<JSONObject> listJson = new ArrayList<JSONObject>();
		MongoCursor<Document> mngc = col.find(doc).iterator();

		while (mngc.hasNext()) {
			Document document = (Document) mngc.next();
			listJson.add(new JSONObject(document.toJson()));
		}

		MongoDB.closeConnection();
		return listJson;
	}

	public static List<JSONObject> getUserComments_NOM(String name) throws JSONException {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		org.bson.Document doc = new org.bson.Document();

		doc.append("nom", name);
		List<JSONObject> listJson = new ArrayList<JSONObject>();
		MongoCursor<Document> mngc = col.find(doc).iterator();
		while (mngc.hasNext()) {
			Document document = (Document) mngc.next();
			listJson.add(new JSONObject(document.toJson()));
		}

		MongoDB.closeConnection();
		return listJson;
	}

	public static List<JSONObject> getCommentForLastNHour(int n) {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		Document doc = new Document("date", DateTools.getFormatedDateAfterNHour(-n));

		return null;
	}

	private static int getLastId() {
		MongoDatabase db = MongoDB.getConnectionToMongoDataBase();
		MongoCollection<org.bson.Document> col = db.getCollection("comments");

		Document doc = new Document("id", -1);

		Document d = col.find().sort(doc).limit(1).first();

		int max_id = d == null ? 0 : d.getInteger("id");

		MongoDB.closeConnection();
		return max_id;
	}

}
