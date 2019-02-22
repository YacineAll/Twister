package com.twister.DataBases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.twister.tools.Tools;

public class SESSION_DB {

	
	private static String queryToGetSession = "SELECT * FROM `SESSION` WHERE ? = ?";
	
	public static boolean insert(String key, int idUser, String dateConnexion) throws SQLException {

		Connection connexion = DataBase.getMySQLConnection();

		String DateFin = Tools.getFormatedDateAfterNHour(+1);

		String query = "INSERT INTO SESSION VALUES('" + key + "', '" + idUser + "' ,'" + dateConnexion + "', '"
				+ DateFin + "')";

		java.sql.Statement s = connexion.createStatement();

		s.executeUpdate(query);

		s.close();
		connexion.close();

		return true;
	}

	public static boolean estDejaConnecte(int idUser) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT cle,Date_fin FROM `SESSION` WHERE id_user=\"" + idUser + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean isConnect = false;
		if (isConnect) {
			query = "UPDATE `SESSION` SET Date_fin \"" + Tools.getFormatedDateAfterNHour(+1) + "\" WHERE id_user=\""
					+ idUser + "\"";
			st.executeUpdate(query);
		}
		rs.close();
		st.close();
		connexion.close();

		return isConnect;
	}

	public static boolean estDejaConnecte(String key) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT cle,Date_fin FROM `SESSION` WHERE cle=\"" + key + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean isConnect = false;
		if (rs.next()) {
			isConnect = rs.getDate("Date_fin").compareTo(new java.util.Date()) < 0;
			if (isConnect) {
				query = "UPDATE `SESSION` SET Date_fin=\"" + Tools.getFormatedDateAfterNHour(+1) + "\" WHERE cle=\""
						+ key + "\"";
				st.executeUpdate(query);
			}
		}
		rs.close();
		st.close();
		connexion.close();

		return isConnect;
	}

	public static boolean removeSession(String key) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();

		String query = "DELETE FROM `SESSION` WHERE cle=\"" + key + "\"";

		java.sql.Statement s = connexion.createStatement();

		s.executeUpdate(query);

		s.close();
		connexion.close();
		return true;
	}

	public static int getIdUserOfKey(String key) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT id_user FROM `SESSION` WHERE cle=\"" + key + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		int idUser = -1;
		if (rs.next()) {
			idUser = rs.getInt("id_user");
		}
		rs.close();
		st.close();
		connexion.close();

		return idUser;

	}
	

}
