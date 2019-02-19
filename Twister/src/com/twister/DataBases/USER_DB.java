package com.twister.DataBases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class USER_DB {

	public static boolean exists(String login) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT * FROM `USER` WHERE LOGIN=\"" + login + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean res = rs.first();
		rs.close();
		st.close();
		connexion.close();
		return res;
	}

	public static boolean checkPassword(String login, String password) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT * FROM `USER` WHERE LOGIN=\"" + login + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.first();
		boolean res = rs.getString("PASSWORD").equals(password);
		rs.close();
		st.close();
		connexion.close();
		return res;
	}

	public static int getId(String login) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "SELECT ID_USER FROM `USER` WHERE LOGIN=\"" + login + "\"";
		Statement st = connexion.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.first();
		int res = rs.getInt("ID_USER");
		rs.close();
		st.close();
		connexion.close();
		return res;
	}

	public static boolean addUSer(String nom, String prenom, String login, String password, String sex, Date birthDay,
			String dateS) throws SQLException {

		Connection connexion = DataBase.getMySQLConnection();
		String query = "INSERT INTO USER VALUES(NULL, '" + login + "', '" + password + "' ,'" + nom + "', '" + prenom
				+ "','" + sex + "','" + birthDay + "','" + dateS + "')";

		java.sql.Statement s = connexion.createStatement();

		s.executeUpdate(query);

		s.close();
		connexion.close();

		return true;
	}
}
