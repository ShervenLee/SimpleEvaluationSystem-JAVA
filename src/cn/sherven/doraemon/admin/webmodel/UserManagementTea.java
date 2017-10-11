package cn.sherven.doraemon.admin.webmodel;

import java.util.List;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

public class UserManagementTea {
	private String userid, key, func;
	private UserTeacher_HC hc;

	private List<UserTeacherH> list = null;
	private int maxpage;

	/**
	 * @return the maxpage
	 */
	public int getMaxpage() {
		return maxpage;
	}

	public UserManagementTea(String userid, String key, String func) {

		hc = new UserTeacher_HC();
		this.userid = userid;
		this.key = key;
		this.func = func;
		maxpage = Funtool.maxitemToMaxPage(hc.getMaxItem());
	}

	public List<UserTeacherH> getList(Integer pagenub) {
		if (pagenub <= 1) {
			pagenub = 1;
		}
		return list = hc.queryAllBy_pagenub(pagenub);
	}
}
