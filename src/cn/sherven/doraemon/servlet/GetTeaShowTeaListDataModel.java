package cn.sherven.doraemon.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.dataModel.IsOKAndErrInfoDataModel;
import cn.sherven.doraemon.dataModel.TeacherShowTeacherEvaluateDataModel;
import cn.sherven.doraemon.hibernate.CommentTeacherH;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.CommentTeacherHC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

/**
 * Servlet implementation class GetTeaShowTeaListDataModel
 */
public class GetTeaShowTeaListDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTeaShowTeaListDataModel() {
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
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		String userid_ = request.getParameter("userid");
		String key_ = request.getParameter("key");

		String json = "";

		if (userid_ == null || key_ == null || userid_.equals("") || key_.equals("")) {
			// 参数不完整
			json = new Gson().toJson(new IsOKAndErrInfoDataModel());
		} else if (Funtool.isKeyOK(userid_, key_)) {

			TeacherShowTeacherEvaluateDataModel model = new TeacherShowTeacherEvaluateDataModel();
			List<CommentTeacherH> list = CommentTeacherHC.queryBy_teacher_id_target(userid_);
			if (list != null) {
				List<Map<String, String>> listmodel = new ArrayList<Map<String, String>>();

				for (int i = 0; i < list.size(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					UserTeacherH queryBy_userid = UserTeacher_HC.queryBy_userid(list.get(i).getTeacher_id_src());
					map.put("name", queryBy_userid.getName());
					map.put("score", list.get(i).getScore());
					map.put("message", list.get(i).getMessage());
					listmodel.add(map);
				}
				model.setList(listmodel);
				json = new Gson().toJson(model);
			}

		} else {
			// 参数错误，APP跳转到登录界面信息
			json = new Gson().toJson(new IsOKAndErrInfoDataModel());
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
