package com.twister.services;

import java.sql.SQLException;

import org.json.JSONObject;

import com.twister.DataBases.SESSION_DB;
import com.twister.tools.JSONResponse;

public class Logout {

	
	public static JSONObject logout(String key) {
		if(key == null) 
			return JSONResponse.serviceRefused("Argument fault ", 1);
		
		try {
			if(!SESSION_DB.estDejaConnecte(key))
				return JSONResponse.serviceRefused("vous n'etes pas connecte", 100);
			
			if(SESSION_DB.removeSession(key))
				return JSONResponse.serviceAccepted();
			return JSONResponse.serviceRefused("SQL PROBLEME IN LOGOUT", 10000);
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEME IN LOGOUT", 10000);
		}
		
	}
}

