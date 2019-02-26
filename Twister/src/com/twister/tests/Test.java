package com.twister.tests;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.services.Friend;
import com.twister.services.User;

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

		JSONObject loginJSO = User.login("yacine1996@outlok.com", "25139");
		
		System.out.println(loginJSO);
		
		Friend.addFriend(loginJSO.getString("Key"), "22");
		Friend.addFriend(loginJSO.getString("Key"), "23");
		
		JSONObject listeFriend = Friend.listeFriend(loginJSO.getString("Key"));
		System.out.println(listeFriend);
		
		Friend.removeFriend(loginJSO.getString("Key"), "23");
		
		
		JSONObject logoutJSO = User.logout(loginJSO.getString("Key"));

		System.out.println(logoutJSO);
	}

}
