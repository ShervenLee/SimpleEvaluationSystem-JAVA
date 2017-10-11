package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.UserStudentH;

public class add_del_up_qur {
	/**
	 * 插入
	 * 
	 * @return
	 */
	public static UserStudentH insert() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			UserStudentH model = new UserStudentH();
			session.save(model);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public static UserStudentH update() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			UserStudentH model = (UserStudentH) session.load(UserStudentH.class, "4028989545a243ca0145a243cbf80001");
			model.setName("王五");

			session.update(model);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public static UserStudentH delete() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			UserStudentH model = (UserStudentH) session.load(UserStudentH.class, "4028989545a244640145a24466300001");

			session.delete(model);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public static UserStudentH queryBy_userid(String studend_id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserStudentH as user_student where user_student.studend_id=:studend_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
//			query.setString("studend_id", studend_id);//过时
			query.setParameter("studend_id", studend_id);
			List<UserStudentH> list = query.list();
			if (list.size() == 1) {
				return list.get(0);
			} else {
				return null;
			}
		} finally {
			if (session != null)
				session.close();
		}
		// try {
		// session = HibernateUtils.getSession();
		// session.beginTransaction();
		//
		// UserStudentH model = (UserStudentH) session.load(UserStudentH.class,
		// "4028989545a243ca0145a243cbf80001");
		// model.getName();
		//
		// session.getTransaction().commit();
		// } catch (Exception e) {
		// e.printStackTrace();
		// session.getTransaction().rollback();
		// } finally {
		// HibernateUtils.closeSession(session);
		// }
	}
}
