package cn.sherven.doraemon.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.id.IncrementGenerator;

import com.google.gson.Gson;

import antlr.build.Tool;
import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.Tool.ToolString;
import cn.sherven.doraemon.dataModel.EvaluateTeacherReturnDataMOdel;
import cn.sherven.doraemon.dataModel.IsOKAndErrInfoDataModel;
import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.CommentHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

/**
 * Servlet implementation class PutTeaEvaTableDataModel
 */
public class PutTeaEvaTableDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PutTeaEvaTableDataModel() {
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
		String messagejson_ = request.getParameter("messagejson");
		if (!(messagejson_ == null || messagejson_.equals(""))) {
			messagejson_ = new String(request.getParameter("messagejson").getBytes("iso-8859-1"), "utf-8");
		}
		String json = "";

		if (userid_ == null || key_ == null || messagejson_ == null || messagejson_.equals("") || userid_.equals("")
				|| key_.equals("")) {
			// 参数不完整
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("参数不完整");
			json = new Gson().toJson(model);
		} else if (Funtool.isKeyOK(userid_, key_)) {
			Gson gson = new Gson();
			try {
				EvaluateTeacherReturnDataMOdel evaluateTeacherReturnDataMOdel = gson.fromJson(messagejson_,
						EvaluateTeacherReturnDataMOdel.class);

				// 执行一些必要的操作evaluateTeacherReturnDataMOdel
				String score = "";
				Map<String, String> map = evaluateTeacherReturnDataMOdel.getReturnServerJson();
				Double buff = 0.0;
				for (Integer i = 0; i < map.size(); i++) {
					buff += Double.parseDouble(map.get(i.toString()));
				}
				score = (buff / map.size()) + "";// 计算平均分

				CommentH model = new CommentH();
				UserStudentH userStumodel = UserStudent_HC.queryBy_userid(userid_);

				//过滤字符串中的连续的回车空格
				String messagebuf = evaluateTeacherReturnDataMOdel.getMessage();
				messagebuf=ToolString.filterDoubleSpaceEnter(messagebuf);
				
				model.setCourses_id(evaluateTeacherReturnDataMOdel.getCourses_id());
				model.setMessage(messagebuf);
				model.setScore(score);
				model.setTeacher_id(evaluateTeacherReturnDataMOdel.getTeacher_id());
				model.setStudent_id(userid_);
				model.setClass_id(userStumodel.getClass_id());

				// 判断是否已有评论记录（有时候客户端会出现多次点击提交按钮）
				System.out.println("\n\n\n\n");
				System.out.println("classid:"+userStumodel.getClass_id());
				System.out.println("coursesid:"+evaluateTeacherReturnDataMOdel.getCourses_id());
				System.out.println("\n\n\n\n");
				List<CommentH> queryBy_classID_coursesID = CommentHC.queryBy_classID_coursesID_stuID(
						userStumodel.getClass_id(), evaluateTeacherReturnDataMOdel.getCourses_id(),userid_);
				if (queryBy_classID_coursesID.size() == 0) {
					CommentHC.insert(model);
				} else {
					System.out.println("已经评论过了");
				}

				IsOKAndErrInfoDataModel returnModel = new IsOKAndErrInfoDataModel();
				returnModel.setErrinfo("null");
				returnModel.setIsok("OK");
				json = new Gson().toJson(returnModel);
			} catch (Exception e) {
				response.getOutputStream().write("失败".getBytes("utf-8"));
				response.getOutputStream().write(messagejson_.getBytes("utf-8"));

			}

		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("key|oldpassword错误");
			json = new Gson().toJson(model);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
