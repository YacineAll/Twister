package com.twister.services;

import java.sql.SQLException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.DataBases.SESSION_DB;
import com.twister.DataBases.USER_DB;
import com.twister.tools.AuthentificationTools;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;
import com.twister.tools.Tools;

public class User {

	
	
	
	

	public static JSONObject createUser(String nom, String prenom, String login, String password, String sex,
			String birth_day) {
		System.out.println(nom+" "+prenom+" "+login+" "+password+" "+sex+" "+birth_day);
		
		if (nom == null || prenom == null || login == null || password == null || sex == null || birth_day == null) {
			return JSONResponse.serviceRefused("Erreur de saisie ", 1);
		}

		if (AuthentificationTools.userExists(login)) {
			return JSONResponse.serviceRefused("utilisateur existe deja", 2);
		}

		try {
			Date birthDay = java.sql.Date
					.valueOf(birth_day.split("/")[2] + "-" + birth_day.split("/")[1] + "-" + birth_day.split("/")[0]);

			USER_DB.addUSer(nom, prenom, login, password, sex, birthDay, Tools.getFormatedDateAfterNHour(0));
			
			return JSONResponse.serviceAccepted();

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEME {creatUser}", 1000);
		}
	}
	
	
	
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
	
	
	
	public static JSONObject logout(String key) {
		if(key == null) 
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		
		try {
			if(!SessionTools.estDejaConnecte(key))
				return JSONResponse.serviceRefused("vous n'etes pas connecte", 2);
			
			if(SESSION_DB.removeSession(key))
				return JSONResponse.serviceAccepted();
			
			return JSONResponse.serviceRefused("SQL PROBLEME {logout}", 1000);
		
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEME {logout}", 1000);
		}
		
	}
}

