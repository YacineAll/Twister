package com.twister.services;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.FRIEND_DB;
import com.twister.DataBases.SESSION_DB;
import com.twister.tools.DateTools;
import com.twister.tools.JSONResponse;

public class Friend {

	public static JSONObject addFriend(String key, String id_Friend) {
		if (key == null | id_Friend == null)
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int id_user = SESSION_DB.getIdUserOfKey(key);
			int idFriend = Integer.parseInt(id_Friend);

			if (FRIEND_DB.isFriend(id_user, idFriend)) {
				return JSONResponse.serviceRefused("Deja amis", 3);
			}

			if (FRIEND_DB.addFriend(id_user, idFriend, DateTools.getFormatedDateAfterNHour(0))) {
				return JSONResponse.serviceAccepted();

			} else
				return JSONResponse.serviceRefused("Erreur sql {addFriend}", 1000);

		} catch (SQLException e) {
			return JSONResponse.serviceRefused("SQL PROBLEME {addFriend}", 1000);
		}
	}

	public static JSONObject removeFriend(String key, String id_Friend) {
		if (key == null | id_Friend == null)
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int id_user = SESSION_DB.getIdUserOfKey(key);
			int idFriend = Integer.parseInt(id_Friend);

			if (!FRIEND_DB.isFriend(id_user, idFriend)) {
				return JSONResponse.serviceRefused("amis introuvable", 3);
			}

			if (FRIEND_DB.removeFriend(id_user, idFriend)) {
				return JSONResponse.serviceAccepted();
			} else
				return JSONResponse.serviceRefused("ERREUR de suppresion d'amis erreur grave", 4);

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("ERREUR SQL IN DELETE FRIEND ", 10000);
		}

	}

	public static JSONObject listeFriend(String key) {
		if (key == null)
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		try {
			if (!SESSION_DB.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int id_user = SESSION_DB.getIdUserOfKey(key);

			List<JSONObject> li = FRIEND_DB.listeOfFriend(id_user);
			if (li.isEmpty())
				return JSONResponse.serviceRefused("liste d'amis vides", 3);

			JSONResponse jsr = JSONResponse.serviceAccepted();
			jsr.put("amis", li);

			return jsr;

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEME {listeFriend}", 1000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("JSON PROBLEM IN {listeFriend}", 100000);
		}
	}

}
