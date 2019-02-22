package com.twister.tools;

import java.sql.SQLException;

import com.twister.DataBases.FRIEND_DB;

public class FriendTools {

	public static boolean addFriend(int idUser, int idFriend) throws SQLException {
		return FRIEND_DB.addFriend(idUser, idFriend, Tools.getFormatedDateAfterNHour(0));
	}

	public static boolean removeFriend(int idUser, int idFriend) throws SQLException {
		return FRIEND_DB.removeFriend(idUser, idFriend);
	}
}
