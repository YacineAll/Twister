package com.twister.tools;

import org.json.JSONObject;

public class JSONResponse extends JSONObject {

	private boolean isAccepted = false;
	private String motif;
	private int code;

	private JSONResponse(boolean isAccepted, String motif, int code) {
		this.isAccepted = isAccepted;
		this.motif = motif;
		this.code = code;
	}

	public static JSONResponse serviceRefused(String msg, int code) {
		return new JSONResponse(false, msg, code);
	}

	public static JSONResponse serviceAccepted() {
		return new JSONResponse(false, "", -1);
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public String getMotif() {
		return motif;
	}

	public int getCode() {
		return code;
	}

}
