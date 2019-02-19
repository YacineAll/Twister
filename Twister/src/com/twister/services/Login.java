package com.twister.services;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.USER_DB;
import com.twister.tools.AuthentificationTools;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;

public class Login {

	public static JSONObject login(String login, String password) {
		if (login == null | password == null)
			return JSONResponse.serviceRefused("Arguments fault", 2);
		if (!AuthentificationTools.userExists(login))
			return JSONResponse.serviceRefused("Unknown user", 0);
		if (!AuthentificationTools.checkPassword(login, password))
			return JSONResponse.serviceRefused("BAd PASSWORD", 3);

		try {
			int idUser = USER_DB.getId(login);
			if (SessionTools.estDejaConnecte(idUser)) {
				return JSONResponse.serviceRefused("vous etes deja connecte", 3);
			}
			DateFormat dateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String dateConnexion = dateF.format(date);

			String key = SessionTools.insertSession(idUser, login, true, dateConnexion);

			JSONObject jse = new JSONObject();
			
			jse.put("ID", idUser);
			jse.put("Login", login);
			jse.put("Key", key);
			
			return jse;
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL ERREUR", 1000);
		} catch (JSONException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("ERREUR JSON LOGIN SERVICE", 10000);
		}

	}

}
