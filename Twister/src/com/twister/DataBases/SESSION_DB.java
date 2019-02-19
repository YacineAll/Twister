package com.twister.DataBases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SESSION_DB {

	public static boolean insert(String key,int idUser, boolean is_Connect,String dateConnexion) throws SQLException{
		
		Connection connexion = DataBase.getMySQLConnection();
		char i = is_Connect? 'Y':'N';
		String query = "INSERT INTO SESSION VALUES('" + key + "', '" + idUser + "' ,'" + i + "', '" + dateConnexion+ "')";

		java.sql.Statement s = connexion.createStatement();

		s.executeUpdate(query);

		s.close();
		connexion.close();


		return true;
	}
	
	public static boolean estDejaConnecte(int idUser) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT isConnect FROM `SESSION` WHERE id_user=\"" + idUser + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		char isConnect = 0;
		if(rs.next()) {
			isConnect = rs.getString("isConnect").toCharArray()[0];
		}
		rs.close();
		st.close();
		connexion.close();
		
		return isConnect == 'Y';
	}
	
	public static boolean estDejaConnecte(String key) throws SQLException{
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT isConnect FROM `SESSION` WHERE cle=\"" + key + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		char isConnect = 0;
		if(rs.next()) {
			isConnect = rs.getString("isConnect").toCharArray()[0];
		}
		rs.close();
		st.close();
		connexion.close();
		
		return isConnect == 'Y';
	}
	
	
	public static boolean removeSession(String key) throws SQLException{
		Connection connexion = DataBase.getMySQLConnection();
		
		String query = "DELETE FROM `SESSION` WHERE cle=\""+key+"\"";

		java.sql.Statement s = connexion.createStatement();

		s.executeUpdate(query);

		s.close();
		connexion.close();
		return true;
	}
	
	public static int getIdUserOfKey(String key) throws SQLException{
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT id_user FROM `SESSION` WHERE cle=\"" + key + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		int idUser = -1;
		if(rs.next()) {
			idUser = rs.getInt("id_user");
		}
		rs.close();
		st.close();
		connexion.close();
		
		return idUser;
	
	}
	
	

}
