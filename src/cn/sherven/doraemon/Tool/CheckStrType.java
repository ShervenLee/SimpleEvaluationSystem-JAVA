package cn.sherven.doraemon.Tool;

public class CheckStrType {

	public static Boolean isInt(String str) {
		if (str == null) {
			return false;
		}
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
