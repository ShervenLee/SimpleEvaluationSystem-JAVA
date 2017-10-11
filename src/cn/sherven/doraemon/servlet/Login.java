package cn.sherven.doraemon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.dataModel.LoginDataModel;
import cn.sherven.doraemon.hibernate.UserAdminH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.UserAdminHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

/**
 * Servlet implementation class Login
 * 
 * 输入参数(POST模式) userid password
 * 
 * 返回参数(JSON格式) isok [OK|NO] errinfo (String) userid (String) key (String) type
 * [student|admin|teacher]
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
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

		String userid_ = request.getParameter("userid");
		String password_ = request.getParameter("password");

		String json = "";

		if (userid_ == null || password_ == null || userid_.equals("") || password_.equals("")) {
			json = errinfo();
		} else if (userid_.length() == 6) {// 学生账号（X）位，教师账号6位
			json = teacherLogin(userid_, password_);
		} else if (userid_.length() == 3) {// 管理员三位
			json = adminLogin(userid_, password_);
		} else {
			json = studentLogin(userid_, password_);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

	public String teacherLogin(String userid_, String password_) {
		LoginDataModel loginDataModel = new LoginDataModel();
		Gson gson = new Gson();
		// 验证账号密码是否正确
		UserTeacherH controller = UserTeacher_HC.queryBy_userid(userid_);
		if (controller != null && controller.getPwd().equals(password_)) {
			// 正确则赋值并返回
			String key = "";
			key = Funtool.getKey(userid_);
			String userid = userid_;
			loginDataModel.setUserid(userid);
			loginDataModel.setType("teacher");
			loginDataModel.setKey(key);
			loginDataModel.setErrinfo("NO");
			loginDataModel.setIsok("OK");
		} else {
			// 错误则返回错误信息
			loginDataModel.setIsok("NO");
			loginDataModel.setErrinfo("账号、密码错误");
		}
		String json = gson.toJson(loginDataModel);
		return json;
	}

	public String studentLogin(String userid_, String password_) {
		LoginDataModel loginDataModel = new LoginDataModel();
		Gson gson = new Gson();
		// 验证账号密码是否正确
		UserStudentH userStudentH = UserStudent_HC.queryBy_userid(userid_);
		System.out.println("1");
		if (userStudentH == null) {
			System.out.println("NULL");
		} else {
			System.out.println("NONULL");
		}
		if (userStudentH != null && userStudentH.getPwd().equals(password_)) {
			// 正确则赋值并返回
			String key = "";
			key = Funtool.getKey(userid_);
			String userid = userid_;
			loginDataModel.setUserid(userid);
			loginDataModel.setType("student");
			loginDataModel.setKey(key);
			loginDataModel.setErrinfo("NO");
			loginDataModel.setIsok("OK");
		} else {
			// 错误则返回错误信息
			loginDataModel.setIsok("NO");
			loginDataModel.setErrinfo("账号、密码错误");
		}
		String json = gson.toJson(loginDataModel);
		return json;
	}

	public String adminLogin(String userid_, String password_) {
		LoginDataModel loginDataModel = new LoginDataModel();
		Gson gson = new Gson();
		// 验证账号密码是否正确
		UserAdminH model = UserAdminHC.queryBy_userid(userid_);
		if (model != null && model.getPwd().equals(password_)) {
			// 正确则赋值并返回
			String key = "";
			key = Funtool.getKey(userid_);
			String userid = userid_;
			loginDataModel.setUserid(userid);
			loginDataModel.setType("admin");
			loginDataModel.setKey(key);
			loginDataModel.setErrinfo("NO");
			loginDataModel.setIsok("OK");
		} else {
			// 错误则返回错误信息
			loginDataModel.setIsok("NO");
			loginDataModel.setErrinfo("账号、密码错误");
		}
		String json = gson.toJson(loginDataModel);
		return json;
	}

	public String errinfo() {
		LoginDataModel loginDataModel = new LoginDataModel();
		Gson gson = new Gson();
		loginDataModel.setIsok("NO");
		loginDataModel.setErrinfo("参数为空或不完整");
		String json = gson.toJson(loginDataModel);
		return json;
	}
}
