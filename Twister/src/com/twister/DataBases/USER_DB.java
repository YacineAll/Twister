package com.twister.DataBases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class USER_DB {

	private static String getUser = "SELECT * FROM `USER` WHERE LOGIN = ?";
	private static String getNameUser = "SELECT * FROM `USER` WHERE ID_USER = ?";
	private static String getPassword = "SELECT password FROM `USER` WHERE login = ?";
	private static String addUser = "INSERT INTO USER (LOGIN, PASSWORD, NOM,PRENOM,SEX,DATE_DE_NAISSANCE,DATE_INSCRIPTION) VALUES (?,?,?,?,?,?,?)";
	private static String getIdUser = "SELECT ID_USER FROM `USER` WHERE LOGIN = ?";

	public static boolean exists(String login) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement st = connexion.prepareStatement(getUser);
		st.setString(1, login);
		ResultSet rs = st.executeQuery();
		boolean res = rs.first();
		rs.close();
		st.close();
		connexion.close();
		return res;
	}

	public static boolean checkPassword(String login, String password) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement st = connexion.prepareStatement(getPassword);
		st.setString(1, login);
		ResultSet rs = st.executeQuery();
		rs.first();
		boolean res = rs.getString("PASSWORD").equals(password);
		rs.close();
		st.close();
		connexion.close();
		return res;
	}

	public static int getId(String login) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement ps = connexion.prepareStatement(getIdUser);
		ps.setString(1, login);
		ResultSet rs = ps.executeQuery();
		rs.first();
		int res = rs.getInt("ID_USER");
		rs.close();
		ps.close();
		connexion.close();
		return res;
	}

	public static boolean addUSer(String nom, String prenom, String login, String password, String sex,
			java.util.Date birthDay, String dateS) throws SQLException {

		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement ps = connexion.prepareStatement(addUser);
		ps.setString(1, login);
		ps.setString(2, password);
		ps.setString(3, nom);
		ps.setString(4, prenom);
		ps.setString(5, sex);
		ps.setDate(6, java.sql.Date.valueOf(birthDay.toString()));
		ps.setString(7, dateS);

		ps.executeUpdate();

		ps.close();
		connexion.close();

		return true;
	}

	public static JSONObject getNameUser(int idUser) throws SQLException {

		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement st = connexion.prepareStatement(getNameUser);
		st.setInt(1, idUser);
		ResultSet rs = st.executeQuery();
		JSONObject name = new JSONObject();
		while (rs.next()) {
			try {
				name.put("nom", rs.getString("NOM"));
				name.put("prenom", rs.getString("PRENOM"));
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		rs.close();
		st.close();
		connexion.close();
		return name;
	}
}
