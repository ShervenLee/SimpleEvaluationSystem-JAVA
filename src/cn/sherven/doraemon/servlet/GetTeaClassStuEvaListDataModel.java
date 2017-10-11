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
import cn.sherven.doraemon.dataModel.TeacherClassStudentEvaluateListDataModel;
import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.CommentHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

/**
 * Servlet implementation class GetTeaClassStuEvaListDataModel
 */
public class GetTeaClassStuEvaListDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTeaClassStuEvaListDataModel() {
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
		String courseid=request.getParameter("courseid");
		String classid=request.getParameter("classid");
		
		String json = "";

		if (userid_ == null || key_ == null ||courseid==null||classid==null ||userid_.equals("") || key_.equals("")||courseid.equals("")||classid.equals("")) {
			// 参数不完整
			IsOKAndErrInfoDataModel isOKAndErrInfoDataModel=new IsOKAndErrInfoDataModel();
			isOKAndErrInfoDataModel.setErrinfo("参数不完整");
			json=new Gson().toJson(isOKAndErrInfoDataModel);
		} else if (Funtool.isKeyOK(userid_, key_)) {
			
			TeacherClassStudentEvaluateListDataModel modelReturn=new TeacherClassStudentEvaluateListDataModel();
			List<CommentH> modelCommentH = CommentHC.queryBy_classID_coursesID(classid, courseid);
			List<Map<String, String>> list=new ArrayList<Map<String,String>>();
			if (modelCommentH!=null&&modelCommentH.size()!=0) {
				for (int i = 0; i < modelCommentH.size(); i++) {
					Map<String, String> map=new HashMap<String, String>();
					
					UserStudentH queryBy_userid = UserStudent_HC.queryBy_userid(modelCommentH.get(i).getStudent_id());
					
					map.put("student_name", queryBy_userid.getName());
					map.put("score", modelCommentH.get(i).getScore());
					map.put("student_message", modelCommentH.get(i).getMessage());
					
					list.add(map);
				}
			}
			modelReturn.setList(list);
			json=new Gson().toJson(modelReturn);
			//json = "{\"isok\":\"OK\",\"errinfo\":\"NO\",\"list\":[{\"class_name\":\"计算机15T1\",\"course_name\":\"安卓开发\",\"score\":\"9.9\",\"sourse_id\":\"123455\",\"class_id\":\"2345678\"},{\"class_name\":\"计算机15T2\",\"course_name\":\"安卓开发2\",\"score\":\"8.8\",\"sourse_id\":\"123455\",\"class_id\":\"2345678\"}]}";
		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel isOKAndErrInfoDataModel=new IsOKAndErrInfoDataModel();
			isOKAndErrInfoDataModel.setErrinfo("参数错误");
			json=new Gson().toJson(isOKAndErrInfoDataModel);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
