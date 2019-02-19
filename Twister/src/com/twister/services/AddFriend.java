package com.twister.services;

import java.sql.SQLException;

import org.json.JSONObject;

import com.twister.tools.FriendTools;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;

public class AddFriend {
	
	public static JSONObject addFriend(String key,String id_Friend) {
		if(key == null | id_Friend == null)
			return JSONResponse.serviceRefused("ARGUMENT FAULT TO ADD FRIEND", 1);
		try {
			if(!SessionTools.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 10);
			}
			int id_user = SessionTools.idUser(key);
			int idFriend = Integer.parseInt(id_Friend);
			if(FriendTools.addFriend(id_user, idFriend)) {
				return JSONResponse.serviceAccepted();
			}else
				return JSONResponse.serviceRefused("ERREUR D'Ajout", 11);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return JSONResponse.serviceRefused("SQL PROBLEME ", 1000);
		}
	}

}
