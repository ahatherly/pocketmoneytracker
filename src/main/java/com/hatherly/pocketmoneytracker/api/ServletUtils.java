package com.hatherly.pocketmoneytracker.api;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
	public static String readParameter(HttpServletRequest request, String key) {
		String result = request.getParameter(key);
		if (result == null) {
			return null;
		} else if (result.length() == 0) {
			return null;
		}
		//TODO: Strip illegal characters from input
		return result;
	}
	
	public static int readIntParameter(HttpServletRequest request, String key) {
		String val = request.getParameter(key);
		if (val == null) {
			return 0;
		}
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
