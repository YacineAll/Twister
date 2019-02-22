package com.twister.services;

import java.sql.SQLException;
import java.util.Date;

import org.json.JSONObject;

import com.twister.DataBases.USER_DB;
import com.twister.tools.AuthentificationTools;
import com.twister.tools.JSONResponse;
import com.twister.tools.Tools;

public class CreateUser {

	public static JSONObject createUser(String nom, String prenom, String login, String password, String sex,
			String birth_day) {
		if (nom == null || prenom == null || login == null || password == null || sex == null || birth_day == null) {
			return JSONResponse.serviceRefused("Argument fault " + login, 1);
		}

		if (AuthentificationTools.userExists(login)) {
			return JSONResponse.serviceRefused("utilisateur existe deja" + login, 2);
		}

		try {
			Date birthDay = java.sql.Date
					.valueOf(birth_day.split("/")[2] + "-" + birth_day.split("/")[1] + "-" + birth_day.split("/")[0]);

			USER_DB.addUSer(nom, prenom, login, password, sex, birthDay, Tools.getFormatedDateAfterNHour(0));
			return JSONResponse.serviceAccepted();

		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("ADD IN USER_DB ERROR", 1000);
		}
	}
}
