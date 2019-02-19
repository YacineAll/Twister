package com.twister.tools;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.twister.DataBases.FRIEND_DB;

public class FriendTools {

	
	
	public static boolean addFriend(int idUser,int idFriend) throws SQLException {
		DateFormat dateF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateInscription = dateF.format(date);
		return FRIEND_DB.addFriend(idUser, idFriend,dateInscription);
	}
	
	public static boolean removeFriend(int idUser,int idFriend) throws SQLException {
		return FRIEND_DB.removeFriend(idUser,idFriend);
	}
}
