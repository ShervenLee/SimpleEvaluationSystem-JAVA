package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import cn.sherven.doraemon.admin.servlet.ConfigAdmin;
import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.UserStudentH;

public class UserStudent_HC {
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public static Boolean resetPassword(String userid, String passwrod) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			UserStudentH model = (UserStudentH) session.load(UserStudentH.class, userid);

			model.setPwd(passwrod);

			session.update(model);
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
	 * 修改手机号
	 * 
	 * @param userid
	 * @param tel
	 * @return
	 */
	public static Boolean resetTel(String userid, String tel) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			UserStudentH model = (UserStudentH) session.load(UserStudentH.class, userid);

			model.setTel(tel);

			session.update(model);
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
	public static UserStudentH queryBy_userid(String studend_id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserStudentH as m where m.student_id=:student_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("student_id", studend_id);
			List<UserStudentH> list = query.list();
			System.out.println("size:" + list.size());
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

	public static boolean insert(UserStudentH model) {
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

	public static boolean insert(List<UserStudentH> model) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			for (UserStudentH userStudentH : model) {
				session.save(userStudentH);
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

	public static List<UserStudentH> queryBy_def_sort_classname(Integer page) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserStudentH as m order by m.class_id asc ";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);

			query.setFirstResult((page - 1) * ConfigAdmin.getPagesize());
			query.setMaxResults(ConfigAdmin.getPagesize());

			List<UserStudentH> list = query.list();
			return list;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static Integer getMaxPageNub() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "select count(*) from UserStudentH";
			// String hql = "from UserStudentH as model order by model.class_id
			// asc ";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			Integer count = Integer.parseInt(String.valueOf(query.uniqueResult()));
			Double maxpage = count / (ConfigAdmin.getPagesize() * 1.0);
			return (int) Math.ceil(maxpage);
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static Boolean del(String[] useridArr) {
		Session session = null;
		session = HibernateUtils.getSession();
		session.beginTransaction();
		UserStudentH model = null;

		for (int i = 0; i < useridArr.length; i++) {
			model = session.get(UserStudentH.class, useridArr[i]);
			if (model != null) {
				session.delete(model);
			}
		}
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public static Boolean del(String userid) {
		Session session = null;
		session = HibernateUtils.getSession();
		session.beginTransaction();
		UserStudentH model = session.get(UserStudentH.class, userid);

		if (model != null) {
			session.delete(model);
			session.getTransaction().commit();
			return true;
		}
		session.close();
		return false;
	}

	public static List<UserStudentH> search(String kwd) {
		Session session = HibernateUtils.getSession();
		session.beginTransaction();
		String query = "from UserStudentH m where ";
		String[] tablekey = { "m.student_id", "m.name", "m.department", "m.major", "m.class_name" };
		for (int i = 0; i < tablekey.length - 2; i++) {
			query += tablekey[i] + " like'%%" + kwd + "%%' or ";
		}
		query += tablekey[tablekey.length - 1] + " like'%%" + kwd + "%%'";
		Query createQuery = session.createQuery(query);
		return createQuery.list();
	}
}
