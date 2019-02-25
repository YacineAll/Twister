package com.twister.tests;

import java.sql.SQLException;

import org.json.JSONException;

import com.twister.DataBases.FRIEND_DB;

public class Test {

//	MongoClient mc = MongoClients.create("mongodb://localhost:27017");
//	MongoDatabase db = mc.getDatabase("Yanis_Yacine");
//	
//	MongoCollection<org.bson.Document> col = db.getCollection("comments");
//	org.bson.Document doc = new org.bson.Document();
//	doc.append("author_id", 15844);
//	doc.append("Name", "Yacine");
//	col.insertOne(doc);
//	
//	GregorianCalendar gc = new java.util.GregorianCalendar() ;
//	gc.add(Calendar.HOUR, -1);
//	
//	System.out.println(gc.getTime());
//	
//	org.bson.Document d = new Document();
//	
//	d.append("author_id", 158);
//	
//	MongoCursor<Document> mCursur = col.find(d).iterator(); 
//	
//	while (mCursur.hasNext()) {
//		Document document = (Document) mCursur.next();
//		System.out.println(document);
//	}

	public static void main(String[] args) throws JSONException, SQLException {

		//JSONObject loginJSO = Login.login("y0658394042@gmail.com", "25139+Yacine");
		
		//System.out.println(loginJSO);
		
		//System.out.println(COMMMENT_DB.getUserComments(23));
		
		//COMMMENT_DB.printMongoDB();
		
		//COMMMENT_DB.addComment(loginJSO.getInt("ID"), "Yacine", "allouache","Saha a riad");
		
		
		System.out.println(FRIEND_DB.isFriend(24, 20));
		
		//JSONObject logoutJSO = Logout.logout(loginJSO.getString("Key"));

		//System.out.println(logoutJSO);
	}

}
