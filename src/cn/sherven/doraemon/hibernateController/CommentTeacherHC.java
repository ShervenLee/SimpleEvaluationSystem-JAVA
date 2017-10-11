package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.CommentTeacherH;
import cn.sherven.doraemon.hibernate.HibernateUtils;

public class CommentTeacherHC {
	/**
	 * 查询
	 * 
	 * @return
	 */
	public static List<CommentTeacherH> queryBy_teacher_id_target(String teacher_id_target) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentTeacherH as model where model.teacher_id_target=:teacher_id_target";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("teacher_id_target", teacher_id_target);
			List<CommentTeacherH> list = query.list();
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
	public static List<CommentTeacherH> queryBy_teacher_id_src(String teacher_id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentTeacherH as model where model.teacher_id_src=:teacher_id_src";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("teacher_id_src", teacher_id);
			List<CommentTeacherH> list = query.list();
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
	public static List<CommentTeacherH> queryBy_teacher_id_srcAndtarget(String teacher_id,String teacher_id_target) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from CommentTeacherH as model where model.teacher_id_src=:teacher_id_src and model.teacher_id_target=:teacher_id_target";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("teacher_id_src", teacher_id);
			query.setParameter("teacher_id_target", teacher_id_target);
			List<CommentTeacherH> list = query.list();
			if (list.size()!=0) {
				return list;
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
	}
	/**
	 * 插入
	 * 
	 * @return
	 */
	public static Boolean insert(CommentTeacherH model) {
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
}
