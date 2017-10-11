package cn.sherven.doraemon.Tool;

public class Config {
	private static final String saltKey = "ShervenLee";
	private static final String saltPassword = "Doraemon";
	private static final String jspRootPath = "/doraemon";
	private static final Integer studentIDLength = 4;
	private static final Integer teacherIDLength = 6;
	private static final Integer adminIDLength = 3;
	// Admin
	public static final String httpRequestPath = "http://localhost:8080/doraemon/";
	private static final Integer pageMaxItem = 15;// 每页最多显示的item条数
	private static final Integer cookieOutTime=3600;//回话过期时间，（秒）

	/**
	 * @return the cookieouttime
	 */
	public static Integer getCookieouttime() {
		return cookieOutTime;
	}

	/**
	 * @return the pagemaxitem
	 */
	public static Integer getPagemaxitem() {
		return pageMaxItem;
	}

	/**
	 * @return the studentidlength
	 */
	public static Integer getStudentidlength() {
		return studentIDLength;
	}

	/**
	 * @return the teacheridlength
	 */
	public static Integer getTeacheridlength() {
		return teacherIDLength;
	}

	/**
	 * @return the adminidlength
	 */
	public static Integer getAdminidlength() {
		return adminIDLength;
	}

	/**
	 * @return the httprequestpath
	 */
	public static String getHttprequestpath() {
		return httpRequestPath;
	}

	/**
	 * @return the saltkey
	 */
	public static String getSaltkey() {
		return saltKey;
	}

	/**
	 * @return the saltpassword
	 */
	public static String getSaltpassword() {
		return saltPassword;
	}

	/**
	 * @return the jsprootpath
	 */
	public static String getJsprootpath() {
		return jspRootPath;
	}
}
