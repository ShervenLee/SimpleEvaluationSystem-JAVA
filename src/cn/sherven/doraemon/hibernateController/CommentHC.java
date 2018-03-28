package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.HibernateUtils;

public class CommentHC {
	/**
	 * 插入
	 * 
	 * @return
	 */
	public static Boolean insert(CommentH model) {
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

	/**
	 * 查询
	 * 
	 * @return
	 */
	public static List<CommentH> queryBy_classID_coursesID(String classid, String coursesid) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentH as model where model.class_id=:class_id and model.courses_id=:courses_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("class_id", classid);
			query.setParameter("courses_id", coursesid);
			List<CommentH> list = query.list();
			if (list != null) {
				return list;
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
	public static List<CommentH> queryBy_classID_coursesID_stuID(String classid, String coursesid,String stuid) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentH as model where model.class_id=:class_id and model.courses_id=:courses_id and model.student_id=:student_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("student_id", stuid);
			query.setParameter("class_id", classid);
			query.setParameter("courses_id", coursesid);
			List<CommentH> list = query.list();
			if (list != null) {
				return list;
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
	public static List<CommentH> queryBy_STUID(String stuid ) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentH as model where model.student_id=:student_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("student_id", stuid);
			List<CommentH> list = query.list();
			if (list != null) {
				return list;
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
	public static List<CommentH> queryBy_TeaID(String teaID ) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentH as m where m.teacher_id=:teacher_id ORDER BY class_id,courses_id,student_id,score";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("teacher_id", teaID);
			List<CommentH> list = query.list();
			if (list != null) {
				return list;
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
}
