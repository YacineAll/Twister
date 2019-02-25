package com.twister.services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.USER_DB;
import com.twister.tools.AuthentificationTools;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;
import com.twister.tools.Tools;

public class Login {

	public static JSONObject login(String login, String password) {
		if (login == null | password == null)
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		if (!AuthentificationTools.userExists(login))
			return JSONResponse.serviceRefused("login non reconnu ", 2);
		if (!AuthentificationTools.checkPassword(login, password))
			return JSONResponse.serviceRefused("mot de passe incorrect", 3);

		try {

			int idUser = USER_DB.getId(login);

			if (SessionTools.estDejaConnecte(idUser)) {
				return JSONResponse.serviceRefused("vous etes deja connecte", 4);
			}

			String key = SessionTools.insertSession(idUser, login, Tools.getFormatedDateAfterNHour(0));

			JSONResponse jse = JSONResponse.serviceAccepted();

			jse.put("ID", idUser);
			jse.put("Login", login);
			jse.put("Key", key);

			return jse;
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEME {login}", 1000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("JSON PROBLEM IN {login}", 100000);
		}

	}

}
