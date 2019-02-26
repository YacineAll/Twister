package com.twister.tests;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.COMMMENT_DB;
import com.twister.services.Comment;
import com.twister.services.Search;
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

//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/firstDataBase");
//			
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		
		JSONObject loginJSO = User.login("yacine1996@outlok.com", "25139");
		
		System.out.println(loginJSO);
//		
//		Friend.addFriend(loginJSO.getString("Key"), "22");
//		Friend.addFriend(loginJSO.getString("Key"), "23");
//		
//		JSONObject listeFriend = Friend.listeFriend(loginJSO.getString("Key"));
//		System.out.println(listeFriend);
//		
//		Friend.removeFriend(loginJSO.getString("Key"), "23");
//		
//		Comment.addComment(loginJSO.getString("Key"), "a taqchicht roh ad tefked");
		
		COMMMENT_DB.printMongoDB();
		
		System.out.println(Search.searchCommentLastNHour(loginJSO.getString("Key"), 1));
		
		JSONObject logoutJSO = User.logout(loginJSO.getString("Key"));
//
		System.out.println(logoutJSO);
	}

}
