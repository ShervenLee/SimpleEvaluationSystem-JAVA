package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.hibernate.ClassTableH;
import cn.sherven.doraemon.hibernate.CourseH;
import cn.sherven.doraemon.hibernate.HibernateUtils;

public class ClassTableHC {
	/**
	 * 查询
	 * 
	 * @return
	 */
	public static ClassTableH queryBy_classTableID(String id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from ClassTableH as classtable where classtable.id_=:id_";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("id_", id);
			List<ClassTableH> list = query.list();
			if (list.size() == 1) {
				return list.get(0);
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
	public static Integer getMaxNub() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "select count(*) from ClassTableH";
			// String hql = "from UserStudentH as model order by model.class_id
			// asc ";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			Integer count = Integer.parseInt(String.valueOf(query.uniqueResult()));
			return count;
		} finally {
			if (session != null)
				session.close();
		}
	}
	public static boolean insert(ClassTableH model) {
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

	public static boolean insert(List<ClassTableH> model) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			for (ClassTableH item : model) {
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
