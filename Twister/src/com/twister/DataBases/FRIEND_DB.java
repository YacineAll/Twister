package com.twister.DataBases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FRIEND_DB {

	
	
	public static boolean addFriend(int idUser,int idFriend,String date) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "INSERT INTO `FRIEND` VALUES ('"+idUser+"','"+idFriend+"','"+date+"')";
		
		Statement st = connexion.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		connexion.close();
		return true;
	}

	public static boolean removeFriend(int idUser, int idFriend) throws SQLException{
		Connection connexion = DataBase.getMySQLConnection();
		String query = "DELETE FROM `FRIEND` WHERE `id_Friend`= \""+ idFriend+"\"" ;
		
		Statement st = connexion.createStatement();
		
		st.executeUpdate(query);
		
		st.close();
		connexion.close();
		return true;
	}
}
