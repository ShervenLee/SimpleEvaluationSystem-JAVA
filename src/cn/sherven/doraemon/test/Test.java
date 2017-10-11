package cn.sherven.doraemon.test;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

public class Test {

	public static void main(String[] args) {
		// System.out.println(UserTeacher_HC.getMaxItem().toString());
		Long long1 = Long.parseLong("19");
		System.out.println(Funtool.maxitemToMaxPage(long1));
	}

}
