package com.twister.services;

import java.sql.SQLException;

import org.json.JSONObject;

import com.twister.DataBases.FRIEND_DB;
import com.twister.tools.FriendTools;
import com.twister.tools.JSONResponse;
import com.twister.tools.SessionTools;

public class AddFriend {

	public static JSONObject addFriend(String key, String id_Friend) {
		if (key == null | id_Friend == null)
			return JSONResponse.serviceRefused("Erreur de saisie", 1);
		try {
			if (!SessionTools.estDejaConnecte(key)) {
				return JSONResponse.serviceRefused("Vous n'etes pas connectee", 2);
			}

			int id_user = SessionTools.idUser(key);
			int idFriend = Integer.parseInt(id_Friend);

			if (FRIEND_DB.isFriend(id_user, idFriend)) {
				return JSONResponse.serviceRefused("Deja amis", 3);
			}

			if (FriendTools.addFriend(id_user, idFriend)) {
				return JSONResponse.serviceAccepted();
				
			} else
				return JSONResponse.serviceRefused("Erreur sql {addFriend}", 1000);

		} catch (SQLException e) {
			return JSONResponse.serviceRefused("SQL PROBLEME {addFriend}", 1000);
		}
	}

}
