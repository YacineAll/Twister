package com.twister.DataBases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.twister.tools.DateTools;

public class SESSION_DB {

	private static final String INSERT_SESSION_QUERY = "INSERT INTO SESSION VALUES(?,?,?,?)";
	private static final String GET_SESSION_FROM_ID_USER = "SELECT cle,Date_fin FROM `SESSION` WHERE id_user= ?";
	private static final String UPDATE_DATE_FIN_ID = "UPDATE `SESSION` SET Date_fin=? WHERE id_user= ?";
	private static final String GET_SESSION_FROM_KEY = "SELECT cle,Date_fin FROM `SESSION` WHERE cle=  ? ";
	private static final String UPDATE_DATE_FIN_KEY = "UPDATE `SESSION` SET Date_fin=? WHERE cle= ?";
	private static final String REMOVE_SESSION = "DELETE FROM `SESSION` WHERE cle= ?";
	private static final String GET_IDUSER_WITH_KEY = "SELECT id_user FROM `SESSION` WHERE cle= ?";

	public static boolean insert(String key, int idUser, String dateConnexion) throws SQLException {

		Connection connexion = DataBase.getMySQLConnection();

		String DateFin = DateTools.getFormatedDateAfterNHour(+1);

		PreparedStatement ps = connexion.prepareStatement(INSERT_SESSION_QUERY);

		ps.setString(1, key);
		ps.setInt(2, idUser);
		ps.setString(3, dateConnexion);
		ps.setString(4, DateFin);

		ps.executeUpdate();

		ps.close();
		connexion.close();

		return true;
	}

	public static boolean estDejaConnecte(int idUser) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement pst = connexion.prepareStatement(GET_SESSION_FROM_ID_USER);
		pst.setInt(1, idUser);

		ResultSet rs = pst.executeQuery();

		boolean isConnect = verif_Update_Date(rs);
		
		rs.close();
		pst.close();

		if (isConnect) {
			pst = connexion.prepareStatement(UPDATE_DATE_FIN_ID);
			pst.setString(1, DateTools.getFormatedDateAfterNHour(+1));
			pst.setInt(2, idUser);
			pst.executeUpdate();
			pst.close();
		}

		connexion.close();

		return isConnect;
	}

	public static boolean estDejaConnecte(String key) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();

		PreparedStatement pst = connexion.prepareStatement(GET_SESSION_FROM_KEY);
		pst.setString(1, key);

		ResultSet rs = pst.executeQuery();

		boolean isConnect = verif_Update_Date(rs);

		rs.close();
		pst.close();

		if (isConnect) {
			pst = connexion.prepareStatement(UPDATE_DATE_FIN_KEY);
			pst.setString(1,DateTools.getFormatedDateAfterNHour(+1));
			pst.setString(2, key);
			pst.executeUpdate();
			pst.close();
		}

		connexion.close();

		return isConnect;
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static boolean verif_Update_Date(ResultSet rs) throws SQLException {
		boolean isConnect = false;

		if (rs.next()) {
			java.util.Date date_fin = null;
			try {
				date_fin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Date_fin"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			isConnect = date_fin.compareTo(new java.util.Date()) > 0;

		}
		return isConnect;
	}

	public static boolean removeSession(String key) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();

		PreparedStatement s = connexion.prepareStatement(REMOVE_SESSION);
		s.setString(1, key);

		s.executeUpdate();

		s.close();
		connexion.close();
		return true;
	}

	public static int getIdUserOfKey(String key) throws SQLException {
		Connection connexion = DataBase.getMySQLConnection();
		PreparedStatement st = connexion.prepareStatement(GET_IDUSER_WITH_KEY);
		st.setString(1, key);

		ResultSet rs = st.executeQuery();
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
