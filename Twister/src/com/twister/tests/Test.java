package com.twister.tests;

import org.json.JSONException;
import org.json.JSONObject;

import com.twister.services.AddFriend;
import com.twister.services.CreateUser;
import com.twister.services.Login;
import com.twister.services.Logout;
import com.twister.services.RemoveFriend;

public class Test {

	public static void main(String[] args) throws JSONException {
		JSONObject b = Login.login("larbimelissa02@gmail.com", "melissa");
		JSONObject createUser = CreateUser.createUser("Yacine", "ALLOUACHE", "y0658394042@gmail.com", "25139+Yacine",
				"M", "22/10/1996");
		JSONObject me = Login.login("y0658394042@gmail.com", "25139+Yacine");

		JSONObject addFriend = AddFriend.addFriend((String) b.get("Key"), (int) me.get("ID") + "");

		System.out.println(addFriend);

		JSONObject removeFriend = RemoveFriend.removeFriend((String) b.get("Key"), (int) me.get("ID") + "");

		System.out.println(removeFriend);

		JSONObject res = Logout.logout((String) b.get("Key"));
		JSONObject dme = Logout.logout((String) me.get("Key"));
	}

}
