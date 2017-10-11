package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.TeacherGroupH;
import cn.sherven.doraemon.hibernate.UserTeacherH;

public class TeacherGroupHC {
	/**
	 * 查询
	 * 
	 * @return
	 */
	public static List<TeacherGroupH> queryTeaAlreadyEvaBy_teacherid(String teacher_id) {
		UserTeacherH model = UserTeacher_HC.queryBy_userid(teacher_id);
		String groupid = model.getTeacher_group();

		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from TeacherGroupH as model where model.groupid=:arg1";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("arg1", groupid);
			List<TeacherGroupH> list = query.list();
			if (list.size() != 0) {
				return list;
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static String queryGroupNameBy_id(String group_id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from TeacherGroupH as model where model.id_=:arg1";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("arg1", group_id);
			List<TeacherGroupH> list = query.list();
			if (list.size() == 1) {
				return list.get(0).getName();
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
	public static boolean insert(TeacherGroupH model) {
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

	public static boolean insert(List<TeacherGroupH> model) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			for (TeacherGroupH user : model) {
				session.save(user);
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
