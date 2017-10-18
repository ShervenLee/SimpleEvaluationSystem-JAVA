package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.Tool.Config;
import cn.sherven.doraemon.admin.servlet.ConfigAdmin;
import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernate.UserTeacherH;

public class UserTeacher_HC {
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

			UserTeacherH model = (UserTeacherH) session.load(UserTeacherH.class, userid);

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
			UserTeacherH model = (UserTeacherH) session.load(UserTeacherH.class, userid);

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
	public static UserTeacherH queryBy_userid(String teacher_id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserTeacherH as user_teacher where user_teacher.teacher_id=:teacher_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("teacher_id", teacher_id);
			List<UserTeacherH> list = query.list();
			System.out.println("list=" + list.size());
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
			String hql = "select count(*) from UserTeacherH";
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

	public static Integer getMaxPageNub() {
		Double maxpage = getMaxNub() / (ConfigAdmin.getPagesize() * 1.0);
		return (int) Math.ceil(maxpage);
	}

	public static Long getMaxItem() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "select count(*) from UserTeacherH";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			return (Long) query.uniqueResult();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static List<UserTeacherH> queryAllBy_pagenub(Integer pagenub) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserTeacherH as model";
			Query query = session.createQuery(hql);
			query.setFirstResult((pagenub - 1) * ConfigAdmin.getPagesize());
			query.setMaxResults(ConfigAdmin.getPagesize());
			List<UserTeacherH> list = query.list();
			return list;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static List<UserTeacherH> queryBy_groupid(String teacher_id) {
		UserTeacherH model = queryBy_userid(teacher_id);
		String groupid = model.getTeacher_group();

		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserTeacherH as user_teacher where user_teacher.teacher_group=:teacher_group";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("teacher_group", groupid);
			List<UserTeacherH> list = query.list();
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

	public static boolean insert(UserTeacherH model) {
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

	public static boolean insert(List<UserTeacherH> model) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			for (UserTeacherH user : model) {
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
