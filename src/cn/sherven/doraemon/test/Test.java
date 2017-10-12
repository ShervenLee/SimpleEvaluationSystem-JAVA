package cn.sherven.doraemon.test;


import java.util.List;

import org.hibernate.Session;

import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

public class Test {

	public static void main(String[] args) {
		
		Session session=HibernateUtils.getSession();
		session.beginTransaction();
		
		
		List<UserStudentH> search = UserStudent_HC.search("");
		if (search != null) {
			for (int i = 0; i < search.size(); i++) {
				System.out.println(search.get(i).getName()+"--"+i);
				
				search.get(i).setMsgtable_id("1");
				session.update(search.get(i));
//				session.getTransaction().commit();
			}
		}
		
		session.getTransaction().commit();
		session.close();
		
		System.exit(0);
	}

}
