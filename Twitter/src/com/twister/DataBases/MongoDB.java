package com.twister.DataBases;

import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	private static MongoClient mc;

	/**
	 * une nouvelle connexion pour mongo 
	 * 
	 * @return {@link MongoDatabase} une nouvelle connexin pour la base mongo 
	 */
	public static MongoCollection<Document> getConnectionToMongoDataBase() {
		mc = MongoClients.create(DBStatic.MONGO_HOST);
		MongoDatabase db = mc.getDatabase(DBStatic.MONGO_DB);
		MongoCollection<org.bson.Document> col = db.getCollection("comments");
		return col;
	}

	public static void closeConnection() {
		mc.close();
	}
	
	@SuppressWarnings("deprecation")
	public static void mapReduce() {
		mc = MongoClients.create(DBStatic.MONGO_HOST);
		MongoDatabase db = mc.getDatabase(DBStatic.MONGO_DB);
		
		
		String m = "function (){\n" + 
				"		    var text = this.comment\n" + 
				"		    var words = this.comment.match(/\\w+/g)\n" + 
				"		    var tf = {}\n" + 
				"\n" + 
				"		    for (var i=0 ; i<words.length;i++){\n" + 
				"		        if(tf[words[i]] == null){\n" + 
				"		            tf[words[i]] = 1\n" + 
				"		        }else{\n" + 
				"		            tf[words[i]] += 1\n" + 
				"		        }\n" + 
				"		    }\n" + 
				"		    for (w in tf){\n" + 
				"		        var rt = {};\n" + 
				"		        rt [id] =  tf[w]\n" + 
				"		        emit(w,rt)\n" + 
				"		    }\n" + 
				"		}";
		String r = "function (key,values){\n" + 
				"    var res = {}\n" + 
				"    for (var i = 0;i<values.length;i++){\n" + 
				"        for (var d in values[i]){\n" + 
				"            res[d] = values[i][d]\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return res\n" + 
				"}";
		
		String f = "function(k,v){\n" + 
				"    var d = object.keys(v).length\n" + 
				"    for (d in v){\n" + 
				"        v[d] = v[d] * Math.log(N/tf)\n" + 
				"    }\n" + 
				"    return v\n" + 
				"}";
		
		Document map = new Document();
		map.put("mapreduce", "comments");
		map.put("map", m);
		map.put("reduce", r);
		map.put("finilize", f);
		map.put("out", "res");
		Document scope = new Document();
		scope.put("N", db.getCollection("comments").count());
		map.put("scope", scope);
		
		MongoCollection<org.bson.Document> col = db.getCollection("comments");
		
		
	}

}
