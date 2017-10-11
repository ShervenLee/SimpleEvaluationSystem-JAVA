package cn.sherven.doraemon.Tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolString {
	public static String filterDoubleSpaceEnter(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}
}
