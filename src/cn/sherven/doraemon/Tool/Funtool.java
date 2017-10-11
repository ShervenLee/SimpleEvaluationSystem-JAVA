package cn.sherven.doraemon.Tool;

import javax.servlet.http.Cookie;

public class Funtool {
	public static String getKey(String userid) {
		return userid;
		// try {
		// MessageDigest md = MessageDigest.getInstance("SHA1");// SHA 或者 MD5
		// BASE64Encoder base = new BASE64Encoder();
		// String str = userid + Config.saltKey;
		// String pwdAfter = base.encode(md.digest(str.getBytes()));
		// return pwdAfter;
		// } catch (Exception e) {
		// return null;
		// }
	}

	public static int maxitemToMaxPage(Long maxitem) {
		Double buf = Double.parseDouble(maxitem.toString()) / Double.parseDouble(Config.getPagemaxitem().toString());
		return (int) Math.ceil(buf);
	}

	public static Boolean isKeyOK(String userid, String key) {
		if (key.equals(getKey(userid))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 暂时明文不做处理
	 * 
	 * @param password
	 * @return
	 */
	public static String hashPassword(String password) {
		String hashString = "";
		hashString = password;
		// try {
		// MessageDigest md = MessageDigest.getInstance("SHA1");// SHA 或者 MD5
		// BASE64Encoder base = new BASE64Encoder();
		// String str = password + Config.saltPassword;
		// hashString = base.encode(md.digest(str.getBytes()));
		// } catch (Exception e) {
		// return null;
		// }
		return hashString;

	}

	public static String isStudentTeacherAdmin(String userid) {
		
		
		if (userid.length() == Config.getAdminidlength()) {
			return "admin";
		} else if (userid.length() == Config.getTeacheridlength()) {
			return "teacher";
		} else {
			return "student";
		}
	}

	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	public static String getUploadPath() {
		return getProjectPath() + "/upload";
	}

	public static Cookie newCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(Config.getCookieouttime());
		return cookie;

	}

	public static String getCookieValue(Cookie[] cookies, String name) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}

	public static String msg(String msg) {
		return "<script language=\"javascript\" type=\"text/javascript\">alert(\"" + "提示函数提示：" + msg + "\");</script>";
	}

}
