package cn.sherven.doraemon.Tool;

public class TestPrivate {
	public static void print(String msg) {
			if (Config.getTest()) {
				System.out.println("提示信息:"+msg);
			}
	}
}
