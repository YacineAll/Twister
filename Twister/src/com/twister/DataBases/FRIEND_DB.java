package com.twister.DataBases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class FRIEND_DB {

	private static final String QUERY_IS_FRIEND = "SELECT * FROM `FRIEND` WHERE id_user = ? and id_Friend = ? ;";
	private static final String LISTE_FRIEND = "SELECT * FROM `FRIEND` WHERE id_user=? ;";

	public static boolean addFriend(int idUser, int idFriend, String date) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "INSERT INTO `FRIEND` VALUES ('" + idUser + "','" + idFriend + "','" + date + "')";

		Statement st = connexion.createStatement();

		st.executeUpdate(query);

		st.close();
		connexion.close();
		return true;
	}

	public static boolean removeFriend(int idUser, int idFriend) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		String query = "DELETE FROM `FRIEND` WHERE `id_Friend`= \"" + idFriend + "\"";

		Statement st = connexion.createStatement();

		st.executeUpdate(query);

		st.close();
		connexion.close();
		return true;
	}

	public static boolean isFriend(int id_user, int id_friend) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement ps = connexion.prepareStatement(QUERY_IS_FRIEND);

		ps.setInt(1, id_user);
		ps.setInt(2, id_friend);

		ResultSet rs = ps.executeQuery();

		boolean resultat = rs.first();

		rs.close();
		connexion.close();

		return resultat;
	}

	public static List<JSONObject> listeOfFriend(int id_user) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement ps = connexion.prepareStatement(LISTE_FRIEND);
		ps.setInt(1, id_user);

		ResultSet rs = ps.executeQuery();

		List<JSONObject> list_json_amis = new ArrayList<JSONObject>();

		while (rs.next()) {
			try {
				JSONObject js = new JSONObject();
				js.put("id", rs.getInt("id_Friend"));
				js.put("nom", USER_DB.getNameUser(rs.getInt("id_Friend")).get("nom"));
				js.put("prenom", USER_DB.getNameUser(rs.getInt("id_Friend")).get("prenom"));
				list_json_amis.add(js);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return list_json_amis;

	}

}
