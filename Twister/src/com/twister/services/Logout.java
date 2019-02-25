package com.twister.services;

import java.sql.SQLException;

import org.json.JSONObject;

import com.twister.DataBases.SESSION_DB;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;

public class Logout {

	
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

