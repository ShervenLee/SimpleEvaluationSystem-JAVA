package cn.sherven.doraemon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.dataModel.IsOKAndErrInfoDataModel;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

/**
 * Servlet implementation class GetResetPasswordDataModel
 */
public class GetResetPasswordDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String json = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetResetPasswordDataModel() {
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
		String key_ = request.getParameter("key");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");

		if (userid_ == null || key_ == null || oldpassword == null || newpassword == null || userid_.equals("")
				|| key_.equals("") || oldpassword.equals("") || newpassword.equals("")) {
			// 参数不完整
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("参数不完整");
			json = new Gson().toJson(model);
		} else {
			String type_ = Funtool.isStudentTeacherAdmin(userid_);
			if (type_.equals("student")) {
				student(userid_, key_, oldpassword, newpassword);
			} else if (type_.equals("teacher")) {
				teacher(userid_, key_, oldpassword, newpassword);
			} else if (type_.equals("admin")) {
				admin(userid_, key_, oldpassword, newpassword);
			} else {
				// 帐号位数错误，
			}
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

	public void student(String userid_, String key_, String oldpassword, String newpassword) {
		if (userid_ == null || key_ == null || oldpassword == null || newpassword == null || userid_.equals("")
				|| key_.equals("") || oldpassword.equals("") || newpassword.equals("")) {
			// 参数不完整
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("参数不完整");
			json = new Gson().toJson(model);
		} else if (Funtool.isKeyOK(userid_, key_)
				&& Funtool.hashPassword(oldpassword).equals(UserStudent_HC.queryBy_userid(userid_).getPwd())) {
			// 修改密码
			Boolean bool = UserStudent_HC.resetPassword(userid_, newpassword);
			if (bool == true) {
				IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
				model.setErrinfo("null");
				model.setIsok("OK");
				json = new Gson().toJson(model);
			}
		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("key|oldpassword错误");
			json = new Gson().toJson(model);
		}
	}

	public void teacher(String userid_, String key_, String oldpassword, String newpassword) {
		if (userid_ == null || key_ == null || oldpassword == null || newpassword == null || userid_.equals("")
				|| key_.equals("") || oldpassword.equals("") || newpassword.equals("")) {
			// 参数不完整
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("参数不完整");
			json = new Gson().toJson(model);
		} else if (Funtool.isKeyOK(userid_, key_)
				&& Funtool.hashPassword(oldpassword).equals(UserTeacher_HC.queryBy_userid(userid_).getPwd())) {
			// 修改密码
			Boolean bool = UserTeacher_HC.resetPassword(userid_, newpassword);
			if (bool == true) {
				IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
				model.setErrinfo("null");
				model.setIsok("OK");
				json = new Gson().toJson(model);
			}
		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("key|oldpassword错误");
			json = new Gson().toJson(model);
		}
	}

	public void admin(String userid_, String key_, String oldpassword, String newpassword) {
	}
}
