package cn.sherven.doraemon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Config;
import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.dataModel.AdminInfoDataModel;
import cn.sherven.doraemon.dataModel.StudentInfoDataModel;
import cn.sherven.doraemon.hibernate.UserAdminH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.UserAdminHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

/**
 * Servlet implementation class GetUserStudentInfo
 * 
 * 输入参数(POST模式) userid key
 * 
 * 返回参数(JSON格式) isok [OK|NO] errinfo (String)
 * 
 * userid name tel type[student|admin|teacher] department major classname
 * 
 */
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		String userid_ = request.getParameter("userid");
		String key_ = request.getParameter("key");

		String json = "";

		if (userid_ == null || key_ == null || userid_.equals("") || key_.equals("")) {
			json = errinfo(null);
		} else if (userid_.length() == Config.getAdminidlength()) {// 学生账号（3）位，教师账号6位
			json = adminInfo(userid_, key_);
		} else if (userid_.length() == Config.getTeacheridlength()) {
			json = teacherInfo(userid_, key_);
		} else{
			json = studentInfo(userid_, key_);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

	public String teacherInfo(String userid_, String key_) {
		StudentInfoDataModel dataModel = new StudentInfoDataModel();
		Gson gson = new Gson();
		// 验证账号密码是否正确
		UserTeacherH userStudentH = UserTeacher_HC.queryBy_userid(userid_);
		if (userStudentH != null && key_.equals(Funtool.getKey(userid_))) {
			// 正确则赋值并返回
			dataModel.setIsok("OK");
			dataModel.setErrinfo("NO");
			dataModel.setUserid(userStudentH.getTeacher_id());
			dataModel.setName(userStudentH.getName());
			dataModel.setTel(userStudentH.getTel());
			dataModel.setType(userStudentH.getType());
		} else {
			// 错误则返回错误信息
			dataModel.setErrinfo("key值错误|对象为空");
		}
		String json = gson.toJson(dataModel);
		return json;
	}

	public String adminInfo(String userid_, String key_) {
		AdminInfoDataModel dataModel = new AdminInfoDataModel();
		Gson gson = new Gson();
		// 验证账号密码是否正确
		UserAdminH userAdminH = UserAdminHC.queryBy_userid(userid_);
		if (userAdminH != null && key_.equals(Funtool.getKey(userid_))) {
			// 正确则赋值并返回
			dataModel.setIsok("OK");
			dataModel.setErrinfo("NO");
			dataModel.setAdmin_id(userAdminH.getAdmin_id());
			dataModel.setName(userAdminH.getName());
			dataModel.setPwd(userAdminH.getPwd());
			dataModel.setLevel(userAdminH.getLevel());
		} else {
			// 错误则返回错误信息
			dataModel.setErrinfo("key值错误|对象为空");
		}
		String json = gson.toJson(dataModel);
		return json;
	}

	public String studentInfo(String userid_, String key_) {
		StudentInfoDataModel dataModel = new StudentInfoDataModel();
		Gson gson = new Gson();
		// 验证账号密码是否正确
		UserStudentH userStudentH = UserStudent_HC.queryBy_userid(userid_);
		if (userStudentH != null && key_.equals(Funtool.getKey(userid_))) {
			// 正确则赋值并返回
			dataModel.setIsok("OK");
			dataModel.setErrinfo("NO");
			dataModel.setUserid(userStudentH.getStudent_id());
			dataModel.setName(userStudentH.getName());
			dataModel.setTel(userStudentH.getTel());
			dataModel.setType(userStudentH.getType());
			dataModel.setDepartment(userStudentH.getDepartment());
			dataModel.setMajor(userStudentH.getMajor());
			dataModel.setClassname(userStudentH.getClass_name());
		} else {
			// 错误则返回错误信息
			dataModel.setErrinfo("key值错误|对象为空");
		}
		String json = gson.toJson(dataModel);
		return json;
	}

	public String errinfo(String errinfo) {
		String def = "参数为空或不完整";
		if (errinfo != null) {
			def = errinfo;
		}
		StudentInfoDataModel dataModel = new StudentInfoDataModel();
		Gson gson = new Gson();
		dataModel.setIsok("NO");
		dataModel.setErrinfo(def);
		String json = gson.toJson(dataModel);
		return json;
	}

}
