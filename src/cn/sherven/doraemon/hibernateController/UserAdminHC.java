package cn.sherven.doraemon.hibernateController;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.admin.servlet.ConfigAdmin;
import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.UserAdminH;
import cn.sherven.doraemon.hibernate.UserTeacherH;

public class UserAdminHC {
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public static Boolean resetPassword(String admin_id, String passwrod) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			UserAdminH model = (UserAdminH) session.load(UserAdminH.class, admin_id);

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
	public static List<UserTeacherH> queryAllBy_pagenub(Integer pagenub) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserAdminH as model";
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
	public static Integer getMaxPageNub() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "select count(*) from UserAdminH";
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
	/**
	 * 查询
	 * 
	 * @return
	 */
	public static UserAdminH queryBy_userid(String admin_id) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "from UserAdminH as user where user.admin_id=:admin_id";// 使用命名参数，推荐使用，易读。
			Query query = session.createQuery(hql);
			query.setParameter("admin_id", admin_id);
			List<UserAdminH> list = query.list();
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
}
