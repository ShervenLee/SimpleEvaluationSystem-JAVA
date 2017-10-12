package cn.sherven.doraemon.test;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

public class Test {

	public static void main(String[] args) {
		List<UserStudentH> search = UserStudent_HC.search("李雪");
		if (search != null) {
			for (int i = 0; i < search.size(); i++) {
				System.out.println(search.get(i).getName());
			}
		}
		System.exit(0);
	}

}
