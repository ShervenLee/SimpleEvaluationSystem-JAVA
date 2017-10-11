package cn.sherven.doraemon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.dataModel.IsOKAndErrInfoDataModel;
import cn.sherven.doraemon.hibernate.MessageTableH;
import cn.sherven.doraemon.hibernateController.MessageTable_HC;

/**
 * Servlet implementation class GetTeaEvaTableDataModel
 */
public class GetTeaEvaTableDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTeaEvaTableDataModel() {
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

		String json = "";

		if (userid_ == null || key_ == null || userid_.equals("") || key_.equals("")) {
			// 参数不完整
			IsOKAndErrInfoDataModel isOKAndErrInfoDataModel = new IsOKAndErrInfoDataModel();
			isOKAndErrInfoDataModel.setErrinfo("参数不完整");
			json = new Gson().toJson(isOKAndErrInfoDataModel);
		} else if (Funtool.isKeyOK(userid_, key_)) {
			MessageTableH model = MessageTable_HC.queryBy_type("teacher");
			json = model.getJson();
		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel isOKAndErrInfoDataModel = new IsOKAndErrInfoDataModel();
			isOKAndErrInfoDataModel.setErrinfo("参数错误");
			json = new Gson().toJson(isOKAndErrInfoDataModel);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
