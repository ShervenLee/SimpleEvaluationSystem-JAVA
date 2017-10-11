package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;

import cn.sherven.doraemon.hibernate.CourseH;
import cn.sherven.doraemon.hibernate.HibernateUtils;

public class CourseHC {
	public static boolean insert(CourseH model) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			session.save(model);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} finally {
			HibernateUtils.closeSession(session);
		}
		return true;
	}

	public static boolean insert(List<CourseH> model) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			for (CourseH item : model) {
				session.save(item);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} finally {
			HibernateUtils.closeSession(session);
		}
		return true;
	}
}
