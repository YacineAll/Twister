package com.twister.services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.COMMMENT_DB;
import com.twister.DataBases.USER_DB;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;

public class AddComment {

	public static  JSONObject addComment(String key, String text) {
		if (text == null | key == null) {
			return JSONResponse.serviceRefused("ARGUMENT FAULT", -1);
		}

		try {
			if (!SessionTools.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("CONNECTION DENIED !!", 255);
			}
			int idUser = SessionTools.idUser(key);
			JSONObject nameUser = USER_DB.getNameUser(idUser);
			if(COMMMENT_DB.addComment(idUser, nameUser.getString("nom"), nameUser.getString("prenom"), text)) {
				return JSONResponse.serviceAccepted();
			};
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("VERIFICATION OF {IF IS CONNECTED WITH KEY} ERROR", 2000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("JSON PROBLEM IN ADD COMMENT", 2526);
		}
		return null;

	}
}
