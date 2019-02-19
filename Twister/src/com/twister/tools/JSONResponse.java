package com.twister.tools;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONResponse {




	public static JSONObject serviceRefused(String msg,int code) {
		JSONObject jso = new JSONObject();
		try {
			jso.put("Erreur: ", msg);
			jso.put("Code: ", code);
		} catch (JSONException e) {
			return jso;
		}
		return jso;
	}

	public static JSONObject serviceAccepted() {
		JSONObject ok = new JSONObject();
		try {

			ok.put("message", "operation réussis");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ok;
	}

}
