package cn.sherven.doraemon.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.Tool.ToolString;
import cn.sherven.doraemon.dataModel.EvaluateTeacherReturnDataMOdel;
import cn.sherven.doraemon.dataModel.IsOKAndErrInfoDataModel;
import cn.sherven.doraemon.dataModel.TeacherEvaluateTeacherReturnDataMOdel;
import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.CommentTeacherH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.CommentHC;
import cn.sherven.doraemon.hibernateController.CommentTeacherHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

/**
 * Servlet implementation class PutTeaEvaTeaTableDataModel
 */
public class PutTeaEvaTeaTableDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PutTeaEvaTeaTableDataModel() {
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
				TeacherEvaluateTeacherReturnDataMOdel evaluateTeacherReturnDataMOdel = gson.fromJson(messagejson_,
						TeacherEvaluateTeacherReturnDataMOdel.class);

				// 执行一些必要的操作evaluateTeacherReturnDataMOdel
				String score = "";
				Map<String, String> map = evaluateTeacherReturnDataMOdel.getReturnServerJson();
				Double buff = 0.0;
				for (Integer i = 0; i < map.size(); i++) {
					buff += Double.parseDouble(map.get(i.toString()));
				}
				score = (buff / map.size()) + "";// 计算平均分

				CommentTeacherH model = new CommentTeacherH();

				// 过滤字符串中的连续的回车空格
				String messagebuf = evaluateTeacherReturnDataMOdel.getMessage();
				messagebuf = ToolString.filterDoubleSpaceEnter(messagebuf);

				model.setMessage(messagebuf);
				model.setScore(score);
				model.setTeacher_id_src(userid_);
				model.setTeacher_id_target(evaluateTeacherReturnDataMOdel.getTeacher_id_target());

				// 判断是否已有评论记录（有时候客户端会出现多次点击提交按钮）
				List<CommentTeacherH> queryBy_classID_coursesID = CommentTeacherHC
						.queryBy_teacher_id_srcAndtarget(userid_, model.getTeacher_id_target());
				if (queryBy_classID_coursesID == null) {
					CommentTeacherHC.insert(model);
					System.out.println("评论成功");
					IsOKAndErrInfoDataModel returnModel = new IsOKAndErrInfoDataModel();
					returnModel.setErrinfo("null");
					returnModel.setIsok("OK");
					json = new Gson().toJson(returnModel);
				} else if (queryBy_classID_coursesID.size() > 0) {
					System.out.println("已经评论过了");
					IsOKAndErrInfoDataModel returnModel = new IsOKAndErrInfoDataModel();
					returnModel.setErrinfo("null");
					returnModel.setIsok("OK");
					json = new Gson().toJson(returnModel);
				} else {
					System.out.println("异常");
					IsOKAndErrInfoDataModel returnModel = new IsOKAndErrInfoDataModel();
					returnModel.setErrinfo("null");
					returnModel.setIsok("NO");
					json = new Gson().toJson(returnModel);
				}

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
