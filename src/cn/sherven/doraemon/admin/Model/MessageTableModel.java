package cn.sherven.doraemon.admin.Model;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import cn.sherven.doraemon.admin.servlet.ConfigAdmin;
import cn.sherven.doraemon.hibernate.HibernateUtils;
import cn.sherven.doraemon.hibernate.MessageTableH;

public class MessageTableModel {

	/**
	 * 添加一条MessageTableH.class记录
	 * 
	 * @param model
	 *            type-MessageTableH
	 * @return
	 */
	public static Boolean add(MessageTableH model) {
		List<MessageTableH> list = new ArrayList<MessageTableH>();
		list.add(model);
		return add(list);
	}

	/**
	 * 添加多条MessageTableH.class记录
	 * 
	 * @param modelList
	 *            type-List<MessageTableH>
	 * @return
	 */
	public static Boolean add(List<MessageTableH> modelList) {
		try {

			Session session = HibernateUtils.getSession();
			session.beginTransaction();
			for (MessageTableH messageTableH : modelList) {
				session.save(messageTableH);
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取List<MessageTableH>
	 * 
	 * @param page
	 *            第一页是1
	 * @return List<MessageTableH>
	 */
	public static List<MessageTableH> getbypage(Integer page) {
		if (page <= 0 || page == null) {
			page = 1;
		}

		Session session = HibernateUtils.getSession();
		session.beginTransaction();

		String hql = "from MessageTableH";
		Query createQuery = session.createQuery(hql);

		createQuery.setFirstResult((page - 1) * ConfigAdmin.getPagesize());
		createQuery.setMaxResults(ConfigAdmin.getPagesize());

		List list = createQuery.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	/**
	 * 获取总数量
	 * 
	 * @return
	 */
	public static Integer getMaxNub() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// from后面是对象，不是表名
			String hql = "select count(*) from MessageTableH";
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

	/**
	 * 最大页数
	 * @return
	 */
	public static Integer getMaxPageNub() {
		Double maxpage = getMaxNub() / (ConfigAdmin.getPagesize() * 1.0);
		return (int) Math.ceil(maxpage);
	}
	/**
	 * 根据主键id获取相应的model
	 * 
	 * @param id
	 * @return List<MessageTableH>
	 */
	public static List<MessageTableH> getbyid(String id) {
		Session session = HibernateUtils.getSession();
		session.beginTransaction();

		String hql = "from MessageTableH as m where m.id_=:id";
		Query createQuery = session.createQuery(hql);
		createQuery.setParameter("id", id);

		List list = createQuery.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static Boolean update(MessageTableH model) {

		try {
			Session session = HibernateUtils.getSession();
			session.beginTransaction();
			session.update(model);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Boolean del(String id) {
		String[] idlist = { id };
		return del(idlist);
	}

	public static Boolean del(String[] idlist) {
		try {
			Session session = HibernateUtils.getSession();
			session.beginTransaction();
			Query createQuery = null;
			String hql = "";
			for (int i = 0; i < idlist.length; i++) {
				if (i == 0) {
					hql = " id_=" + idlist[i];
				} else {
					hql = hql + " or id_=" + idlist[i];
				}
			}
			// from 后面跟的是Hib里面的类名
			Query q = session.createQuery("delete from MessageTableH where " + hql);
			q.executeUpdate();
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

}
