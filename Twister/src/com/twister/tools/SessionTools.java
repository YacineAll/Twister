package com.twister.tools;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.twister.DataBases.SESSION_DB;

public class SessionTools {

	public static String insertSession(int idUser, String login, String dateConnexion) throws SQLException {

		String key = generateKey(idUser, login);
		SESSION_DB.insert(key, idUser,dateConnexion);
		return key;

	}
	
	
	public static boolean removeSession(String key) throws SQLException {
		return SESSION_DB.removeSession(key);
	}
	public static boolean estDejaConnecte(int idUser) throws SQLException {
		return SESSION_DB.estDejaConnecte(idUser);
	}

	
	public static boolean estDejaConnecte(String key) throws SQLException {
		return SESSION_DB.estDejaConnecte(key);
	}
	
	private static String generateKey(int idUser, String login) {
		List<Character> list = IntStream
				.range(0,
						(IntStream.rangeClosed('A', 'Z').mapToObj(c -> "" + (char) c).collect(Collectors.joining())
								+ "1234567890" + idUser + login).toCharArray().length)
				.mapToObj(
						i -> (IntStream.rangeClosed('A', 'Z').mapToObj(c -> "" + (char) c).collect(Collectors.joining())
								+ "1234567890" + idUser + login).toCharArray()[i])
				.collect(Collectors.toList());
		Collections.shuffle(list);
		String str = "";
		for (Character character : list) {
			str = str + character;
		}
		return str;
	}
	
	public static int idUser(String key) throws SQLException{
		return SESSION_DB.getIdUserOfKey(key);
	}
	
}
