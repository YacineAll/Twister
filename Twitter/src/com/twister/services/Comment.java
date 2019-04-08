package com.twister.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.COMMMENT_DB;
import com.twister.DataBases.FRIEND_DB;
import com.twister.DataBases.SESSION_DB;
import com.twister.DataBases.USER_DB;
import com.twister.tools.JSONResponse;

public class Comment {

	public static JSONObject addComment(String key, String text) {

		if (text == null | key == null) {
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		}

		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int idUser = SESSION_DB.getIdUserOfKey(key);
			JSONObject nameUser = USER_DB.getNameUser(idUser);

			return COMMMENT_DB.addComment(idUser, nameUser.getString("nom"), nameUser.getString("prenom"), text)
					? JSONResponse.serviceAccepted()
					: JSONResponse.serviceRefused("erreur inatendue", 3);

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("Erreur sql {addComment}", 2000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("JSON PROBLEM IN {ADD COMMENT}", 100000);
		}
	}
	
	
	public static JSONObject friendsComments(String key) {

		if ( key == null) {
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		}

		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			
			int idUser = SESSION_DB.getIdUserOfKey(key);
			
			List<Integer> ids = new ArrayList<Integer>();
			List<JSONObject> friends =  FRIEND_DB.listeOfFriend(idUser);
			
			if (friends.isEmpty())
				return JSONResponse.serviceRefused("liste d'amis vides", 3);
			
			for (JSONObject jsonObject : friends) {
				ids.add(jsonObject.getInt("id"));
			}
			
			List<JSONObject> friendsComments = COMMMENT_DB.listeCommentsOfUsers(ids);
			if(friendsComments.isEmpty()) {
				return JSONResponse.serviceRefused("liste de commentaires vides", 4);
			}
			JSONResponse jr = JSONResponse.serviceAccepted();
			jr.put("FriendsComments", friendsComments);
			
			return jr;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("Erreur sql {addComment}", 2000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("JSON PROBLEM IN {ADD COMMENT}", 100000);
		}
	}

	public static JSONObject removeComment(String key, String id) {

		if (id == null | key == null) {
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		}

		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int id_user = SESSION_DB.getIdUserOfKey(key);
			int id_comment = Integer.parseInt(id);

			return COMMMENT_DB.removeComment(id_user, id_comment) ? JSONResponse.serviceAccepted()
					: JSONResponse.serviceRefused("erreur inatendue", 3);

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("Erreur sql {addComment}", 2000);
		}
	}
	
	/**
	 *  lister les messages de l'utilisateur
	 * @param key
	 * @return JSONObject {resultat:JSONArray}
	 */
	public static JSONObject UserComment(String key) {
		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("connexion denied", 1);
			}
			int id_user = SESSION_DB.getIdUserOfKey(key);
			List<JSONObject> list = COMMMENT_DB.getUserCommentsId_Author(id_user);

			JSONObject js = JSONResponse.serviceAccepted();
			js.put("Comments", list);
			return js;

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEM {searchMyComments}", 1000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("JSON PROBLEM {searchMyComments}", 10000);
		}
	}
}
