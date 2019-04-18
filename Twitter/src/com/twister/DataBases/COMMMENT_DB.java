package com.twister.DataBases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.twister.tools.DateTools;

/**
 * @author root
 *
 *         Data Base of comments like :
 *         {"_id":idOfMongo,"id":idcomment,"author_id":idUser,"nom":nom,"prenom",prenom,"date",dateDeCreation,"comment":le
 *         commentaire}
 *
 *
 */
public class COMMMENT_DB {

	private static int id = getLastId() + 1;

	/**
	 * Affiche la base de donne comment
	 * 
	 */
	public static void printMongoDB() {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();

		MongoCursor<Document> mCursur = col.find().iterator();
		try {
			while (mCursur.hasNext()) {
				Document document = (Document) mCursur.next();
				
				@SuppressWarnings("unchecked")
				ArrayList<Document> replies = (ArrayList<Document>) document.get("replies");
				@SuppressWarnings("unchecked")
				ArrayList<Document> likes= (ArrayList<Document>) document.get("Likes");
				
				JSONObject jse = new JSONObject();
				jse.put("id", document.getInteger("id"));
				jse.put("author_id", document.getInteger("author_id"));
				jse.put("nom", document.getString("nom"));
				jse.put("prenom", document.getString("prenom"));
				jse.put("date", document.getDate("date"));
				jse.put("comment", document.getString("comment"));
				jse.put("likes", likes);
				System.out.println(jse);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		MongoDB.closeConnection();
	}

	/**
	 * Ajout un commentaire dans la base de donnee de mongo Cree un message de cette
	 * façoon
	 * {"id":idcomment,"author_id":idUser,"nom":nom,"prenom",prenom,"date",dateDeCreation,"comment":le
	 * commentaire}
	 * 
	 * 
	 * @param idUser  Integer l'identifiant de l'utilisateur
	 * @param nom     String le nom de l'utilisateur
	 * @param prenom  String le prenom de l'utilisateur
	 * @param comment String le contenu de message
	 * @return boolean true si l'operation est bien passe false si non
	 */
	public static boolean addComment(int idUser, String nom, String prenom, String comment,Date date) {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();
		
		
		org.bson.Document doc = new org.bson.Document();

		
		
		doc.append("id", id++);
		doc.append("author_id", idUser);
		doc.append("nom", nom);
		doc.append("prenom", prenom);
		doc.append("date", date);
		doc.append("comment", comment);
		doc.append("replies", new ArrayList<>());
		doc.append("Likes", new ArrayList<>());

		col.insertOne(doc);
		
		MongoDB.closeConnection();
		
		MongoDB.mapReduce();
		return true;
	}
	
	
	public static boolean addLike(int idUser,int idComment) {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();
		
		org.bson.Document docS = new org.bson.Document();
		docS.append("id", idComment);
		col.updateOne(docS, Updates.addToSet("Likes", idUser));
		MongoDB.closeConnection();

		return true;
	}


	
	public static boolean removeLike(int idUser,int idComment) {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();
		
		org.bson.Document docS = new org.bson.Document();
		docS.append("id", idComment);
		
		col.updateOne(docS, Updates.pull("Likes", idUser));
		MongoDB.closeConnection();

		return true;
	}
	public static boolean addReplys(int idUser, String nom, String prenom, String comment,Date date,int idComment) {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();
		
		
		org.bson.Document reply = new org.bson.Document();
		reply.append("id", id++);
		reply.append("author_id", idUser);
		reply.append("nom", nom);
		reply.append("prenom", prenom);
		reply.append("date", date);
		reply.append("comment", comment);
		reply.append("Likes", new ArrayList<>());

		
		org.bson.Document docS = new org.bson.Document();
		docS.append("id", idComment);
		col.updateOne(docS, Updates.addToSet("replies", reply));
		
		MongoDB.closeConnection();

		return true;
	}

	/**
	 * Supprime un commentaire en donnant son identifiant et l'identifiant de
	 * l'utilisateur retourne false si iduser != id_author de comentaire
	 * 
	 * @param id_user   Integer l'identifiant de l'utilisateur qui veux supprmier le
	 *                  commentaire
	 * @param idComment Integer l'identifiant de commentaire
	 * @return boolean true si l'operation est reussie false si non
	 */
	public static boolean removeComment(int id_user, int idComment) {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();

		org.bson.Document d = new Document();
		d.append("id", idComment);
		System.out.println(col.find(d));
		try {
			if (col.find(d).first().getInteger("author_id") == id_user) {
				col.deleteMany(d);
				return true;
			}

		} finally {
			MongoDB.closeConnection();
			MongoDB.mapReduce();
		}

		return false;
	}
	
	public static JSONObject commentId(int idComment) {
		try {
			JSONObject comment = new JSONObject();
			MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();
			org.bson.Document d = new Document();
			d.append("id", idComment);
			Document document = col.find(d).first();

			@SuppressWarnings("unchecked")
			ArrayList<Document> replies = (ArrayList<Document>) document.get("replies");
			@SuppressWarnings("unchecked")
			ArrayList<Document> likes= (ArrayList<Document>) document.get("Likes");
			
			String dateR = document.getDate("date").toString();
			dateR = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH).parse(dateR));
			
			comment.put("id", document.getInteger("id"));
			comment.put("author_id", document.getInteger("author_id"));
			comment.put("nom", document.getString("nom"));
			comment.put("prenom", document.getString("prenom"));
			comment.put("date", dateR);
			comment.put("comment", document.getString("comment"));
			comment.put("replies", replies);
			comment.put("likes", likes);
			
			return comment;
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			MongoDB.closeConnection();
		}
		return null;
	}
	
	public static List<JSONObject> commentsWithIds(List<Integer> ids){
		List<JSONObject> comments = new ArrayList<>();
		for (int id : ids) {
			comments.add(commentId(id));
		}
		return comments;
	}

	public static List<JSONObject> getUserCommentsId_Author(int id_author) throws JSONException {
		
		try {
			
			MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();

			org.bson.Document doc = new org.bson.Document();

			doc.append("author_id", id_author);
			List<JSONObject> listJson = new ArrayList<JSONObject>();
			MongoCursor<Document> mngc = col.find(doc).iterator();

			
			while (mngc.hasNext()) {
				Document document = (Document) mngc.next();
				JSONObject jse = new JSONObject();
				@SuppressWarnings("unchecked")
				ArrayList<Document> replies = (ArrayList<Document>) document.get("replies");
				@SuppressWarnings("unchecked")
				ArrayList<Document> likes= (ArrayList<Document>) document.get("Likes");
				
				String dateR = document.getDate("date").toString();
				dateR = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH).parse(dateR));
				
				jse.put("id", document.getInteger("id"));
				jse.put("author_id", document.getInteger("author_id"));
				jse.put("nom", document.getString("nom"));
				jse.put("prenom", document.getString("prenom"));
				jse.put("date", dateR);
				jse.put("comment", document.getString("comment"));
				jse.put("likes", likes);
				
				
				ArrayList<JSONObject> repliesJS = new ArrayList<>();
				for (Document reply : replies) {
					dateR = reply.getDate("date").toString();
					dateR = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH).parse(dateR));
					
					
					JSONObject replyJS = new JSONObject();
					replyJS.put("id", reply.getInteger("id"));
					replyJS.put("author_id", reply.getInteger("author_id"));
					replyJS.put("nom", reply.getString("nom"));
					replyJS.put("prenom", reply.getString("prenom"));
					replyJS.put("date", dateR);
					replyJS.put("comment", reply.getString("comment"));
					replyJS.put("likes", likes);
					
					repliesJS.add(replyJS);
					
				}
				
				jse.put("replies",repliesJS);
				listJson.add(jse);
			}
			return listJson;
		} catch (ParseException e) {
			MongoDB.closeConnection();
			e.printStackTrace();
			return null;
		}
}

	/**
	 * Lister les message de l'utilisateur par son nom ou prenom
	 * 
	 * @param name String le nom de l'utilisateur
	 * @return List<JSONObject> qui est une liste des json tel que chaque jason
	 *         contient un commentaire
	 * @throws JSONException exception de JSON
	 */
	public static List<JSONObject> getUserComments_NOM(String name) throws JSONException {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();

		org.bson.Document doc = new org.bson.Document();
		doc.append("nom", name);
		List<JSONObject> listJson = new ArrayList<JSONObject>();
		MongoCursor<Document> mngc = col.find(doc).iterator();
		while (mngc.hasNext()) {
			Document document = (Document) mngc.next();
			listJson.add(new JSONObject(document.toJson()));
		}
		org.bson.Document docprenom = new org.bson.Document();
		doc.append("prenom", name);
		MongoCursor<Document> mng = col.find(docprenom).iterator();
		while (mng.hasNext()) {
			Document document = (Document) mng.next();
			JSONObject jse = new JSONObject();
			String date2 = document.getDate("date").toString();
			try {
				date2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH).parse(date2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jse.put("id", document.getInteger("id"));
			jse.put("author_id", document.getInteger("author_id"));
			jse.put("nom", document.getString("nom"));
			jse.put("prenom", document.getString("prenom"));
			jse.put("date", date2);
			jse.put("comment", document.getString("comment"));
			jse.put("replies", document.getList("replies", ArrayList.class));
			
			listJson.add(jse);
		}

		MongoDB.closeConnection();
		return listJson;
	}

	public static List<JSONObject> getCommentForLastNHour(int n) {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();

		Document doc = new Document();

		doc.append("date", new Document("$gt", DateTools.getDateAfterNHour(-1 * n)));

		List<JSONObject> listJson = new ArrayList<JSONObject>();

		MongoCursor<Document> cursor = col.find(doc).iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				JSONObject jse = new JSONObject();
				String date2 = document.getDate("date").toString();
				try {
					date2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH).parse(date2));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				jse.put("id", document.getInteger("id"));
				jse.put("author_id", document.getInteger("author_id"));
				jse.put("nom", document.getString("nom"));
				jse.put("prenom", document.getString("prenom"));
				jse.put("date", date2);
				jse.put("comment", document.getString("comment"));
				jse.put("replies", document.getList("replies", ArrayList.class));
				listJson.add(jse);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			MongoDB.closeConnection();
		}

		return listJson;
	}
	
	public static List<JSONObject> listeCommentsOfUsers(List<Integer> ids) {
		List<JSONObject> array = new ArrayList<>();
		for (Integer id  : ids) {
			try {
				array.addAll(getUserCommentsId_Author(id));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	/**
	 * retourne le dernier id de la base pour differencier les commentaires
	 * 
	 * @return Integer le dernier id fournis
	 */
	private static int getLastId() {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();

		Document doc = new Document("id", -1);

		Document d = col.find().sort(doc).limit(1).first();

		int max_id = d == null ? 0 : d.getInteger("id");

		MongoDB.closeConnection();
		return max_id;
	}
	
	public static int getId() {
		return id;
	}
	
	
	
	
	public static List<JSONObject> search(String word) throws JSONException {
		
		DBCollection collection = MongoDB.getConnectionToMongoMapReduceDataBase("out");
		BasicDBObject query = new BasicDBObject();
		
		query.put("_id", word);
		DBObject dbo  = collection.findOne(query);
		
		if(dbo == null) {
			return null;
		}
		BasicDBObject value = (BasicDBObject) dbo.get("value");
		Set<Entry<String, Object>> set = value.entrySet();
		List<Integer> list = set.stream()
							.sorted((s1,s2) -> Double.compare(Double.parseDouble(s1.getValue().toString()), Double.parseDouble(s1.getValue().toString())) )
							.map((e)->Integer.parseInt(e.getKey())).collect(Collectors.toList());
		
		List<JSONObject> comments = new ArrayList<>();
		comments = commentsWithIds(list);
		MongoDB.closeConnectionMapReduce();
		return comments;
	}

	public static void clearAllComment() {
		MongoCollection<org.bson.Document> col = MongoDB.getConnectionToMongoDataBase();
		col.drop();
		MongoDB.closeConnection();
	}
}
