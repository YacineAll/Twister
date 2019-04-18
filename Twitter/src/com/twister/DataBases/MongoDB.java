package com.twister.DataBases;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	private static MongoClient mc;
	private static com.mongodb.MongoClient mongoClient;
	
	
	
	
	public static DBCollection getConnectionToMongoMapReduceDataBase(String databse) {
		mongoClient= new com.mongodb.MongoClient(new ServerAddress("localhost", 27017));
		@SuppressWarnings("deprecation")
		DB db =  mongoClient.getDB(DBStatic.MONGO_DB);
		DBCollection col = db.getCollection(databse);
		return col;
	}
	
	public static void closeConnectionMapReduce() {
		mongoClient.close();
	}

	
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
	
	
	public static void mapReduce() {
		
		String m = "function (){" + 
				"		    var text = this.comment;" + 
				"		    var words = this.comment.match(/\\w+/g);" + 
				"		    var tf = {};" + 
				" " + 
				"		    for (var i=0 ; i<words.length;i++){ " +
				"               mot = words[i].toLowerCase();          "+
				"		        if(tf[mot] == null){ " + 
				"		            tf[mot] = 1;" + 
				"		        }else{ " + 
				"		            tf[mot] += 1;" + 
				"		        } " + 
				"		    } " +
				"		    for (w in tf){ " + 
				"		        var rt = {}; " + 
				"		        rt [this.id] =  tf[w];" + 
				"		        emit(w,rt);" + 
				"		    } " + 
				"		}";
		String r = "function (key,values){ " + 
				"    var res = {};" + 
				"    for (var i = 0;i<values.length;i++){ " + 
				"        for (var d in values[i]){ " + 
				"            res[d] = values[i][d];" + 
				"        };" + 
				"    };" + 
				"    return res;" + 
				"}";
		
		String f = "function(k,v){ " + 
				"    var d = Object.keys(v).length;" + 
				"    for (d in v){ " + 
				"        v[d] = v[d] * Math.log(N/d);" + 
				"    } " + 
				"    return v;" + 
				"}";
		

		DBCollection collection = getConnectionToMongoMapReduceDataBase("comments");
		
		MapReduceCommand cmd = new MapReduceCommand(collection, m, r, "out", MapReduceCommand.OutputType.REPLACE, null);
		cmd.setFinalize(f);
		BasicDBObject  n = new BasicDBObject ();
		n.put("N", collection.count());
		cmd.setScope(n);
		collection.mapReduce(cmd);
		
		closeConnectionMapReduce();
	}

}
