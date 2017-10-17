package cn.sherven.doraemon.admin.servlet;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.Tool.TestPrivate;
import cn.sherven.doraemon.hibernate.UserAdminH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.UserAdminHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;
import cn.sherven.doraemon.test.Test;

/**
 * Servlet implementation class User
 */
public class AUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * action <get|del|update|add>
	 * 
	 * action=get type <admin|teacher|student>
	 * 
	 * id与page必须选择其中一项 id [!userid] page [!number]
	 * 
	 * action=del
	 * 
	 * action=add
	 * 
	 * action=update
	 * 
	 */
	private String action, type, userid, page;
	private Map<String, Object> map;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AUser() {
		super();
		this.map = new HashMap<String, Object>();
		map.put("isok", "ok");
		map.put("errinfo", "null");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		this.request = request;
		this.response = response;

		this.action = request.getParameter("action");
		if (this.action == null || this.action.equals("")) {
			map.put("isok", "err");
			map.put("errinfo", "action null");
			response.getWriter().append(new Gson().toJson(map));
			return;
		}
		switch (action) {
		case "get":
			String type = request.getParameter("type") == null ? "stu" : request.getParameter("type");
			switch (type) {
			case "stu":
				getStu();
				break;
			case "tea":
				getTea();
				break;
			case "admin":
				getAdmin();
				break;
			default:
				getStu();
				break;
			}
			break;
		case "add":
			add();
			break;
		case "del":
			delStu();
			break;
		case "update":
			update();
			break;
		default:
			map.put("isok", "ok");
			map.put("errinfo", "action err");
		}
	}

	private void getStu() throws IOException {
		String page = request.getParameter("page");
		String userid = request.getParameter("userid");
		if (userid != null && userid.equals("") == false) {
			UserStudentH model = UserStudent_HC.queryBy_userid(userid);
			map.put("model", model);
			response.getWriter().append(new Gson().toJson(map));
			return;
		}else {
			if (page == null || page.equals("")) {
				page = "1";
			}
			List<UserStudentH> list = UserStudent_HC.queryBy_def_sort_classname(Integer.parseInt(page));

			map.put("list", list);
			map.put("maxpage", UserStudent_HC.getMaxPageNub());
			map.put("currpage", page);
			map.put("type", "stu");
			response.getWriter().append(new Gson().toJson(map));
		}
	}

	private void delStu() throws IOException {
		String userid = request.getParameter("userid");
		System.out.println("====" + userid + "------------");
		if (userid.equals("") || userid == null) {
			this.map.put("isok", "no");
			this.map.put("errinfo", "userid null");
			return;
		}
		UserStudent_HC.del(userid);
		this.map.put("deluserid", userid);
		this.map.put("isok", "ok");
		response.getWriter().append(new Gson().toJson(map));
	}

	private void getTea() throws IOException {
		String page = request.getParameter("page");
		String userid = request.getParameter("userid");
		if (userid != null && userid.equals("") == false) {
			UserTeacherH model = UserTeacher_HC.queryBy_userid(userid);
			map.put("model", model);
			response.getWriter().append(new Gson().toJson(map));
			System.out.println("--");
			return;
		}else {
			TestPrivate.print("============");
			if (page == null || page.equals("")) {
				page = "1";
			}
			List<UserTeacherH> list = UserTeacher_HC.queryAllBy_pagenub(Integer.parseInt(page));
			map.put("list", list);
			map.put("maxpage", UserTeacher_HC.getMaxPageNub());
			map.put("currpage", page);
			map.put("type", "tea");
			response.getWriter().append(new Gson().toJson(map));
		}
		
	}

	private void getAdmin() throws IOException {
		String page = request.getParameter("page");
		String userid = request.getParameter("userid");
		if (userid != null && userid.equals("") == false) {
			UserAdminH model = UserAdminHC.queryBy_userid(userid);
			map.put("model", model);
			response.getWriter().append(new Gson().toJson(map));
			return;
		}

		if (page == null || page.equals("")) {
			page = "1";
		}
		List<UserTeacherH> list = UserAdminHC.queryAllBy_pagenub(Integer.parseInt(page));

		map.put("list", list);
		map.put("maxpage", UserAdminHC.getMaxPageNub());
		map.put("currpage", page);
		map.put("type", "admin");
		response.getWriter().append(new Gson().toJson(map));
	}

	private void search() {
		String kwd = request.getParameter("kwd");
	}

	private void add() {

	}

	private void update() {
	}

}
