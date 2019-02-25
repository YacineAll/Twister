package com.twister.services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.COMMMENT_DB;
import com.twister.DataBases.USER_DB;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;

public class Comment {

	public static JSONObject addComment(String key, String text) {

		if (text == null | key == null) {
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		}

		try {
			if (!SessionTools.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int idUser = SessionTools.idUser(key);
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

	public static JSONObject removeComment(String key, String id) {

		if (id == null | key == null) {
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		}

		try {
			if (!SessionTools.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int id_user = SessionTools.idUser(key);
			int id_comment = Integer.parseInt(id);

			return COMMMENT_DB.removeComment(id_user, id_comment) ? JSONResponse.serviceAccepted()
					: JSONResponse.serviceRefused("erreur inatendue", 3);

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("Erreur sql {addComment}", 2000);
		}
	}
}
