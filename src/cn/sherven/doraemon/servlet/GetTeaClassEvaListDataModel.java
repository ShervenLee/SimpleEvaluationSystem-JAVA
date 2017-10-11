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
import cn.sherven.doraemon.dataModel.TeacherClassEvaluateListDataModel;
import cn.sherven.doraemon.hibernate.ClassTableH;
import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.ClassTableHC;
import cn.sherven.doraemon.hibernateController.CommentHC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

/**
 * Servlet implementation class GetTeaClassEvaListDataModel
 */
public class GetTeaClassEvaListDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTeaClassEvaListDataModel() {
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
			TeacherClassEvaluateListDataModel returnmodel = new TeacherClassEvaluateListDataModel();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();

			UserTeacherH model = UserTeacher_HC.queryBy_userid(userid_);
			String class_id_list = model.getClass_id_list();
			String buf[] = class_id_list.split(",");
			for (int i = 0; i < buf.length; i++) {

				ClassTableH classTableH = ClassTableHC.queryBy_classTableID(buf[i]);

				String course_id_list = classTableH.getCourse_id_list();
				String course_name_list = classTableH.getCourse_name_list();
				String teacher_id_list = classTableH.getTeacher_id_list();
				String[] course_id_list_arr = course_id_list.split(",");
				String[] course_name_list_arr = course_name_list.split(",");
				String[] teacher_id_list_arr = teacher_id_list.split(",");

				String index = "e";
				for (int j = 0; j < teacher_id_list_arr.length; j++) {
					if (teacher_id_list_arr[j].equals(userid_)) {
						
						if (index.equals("e")) {
							index= j+"";
						} else {
							index += "," + j;
						}
					}
				}
				
				String[] index_arr = index.split(",");
				
				for (int j = 0; j < index_arr.length; j++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("class_name", classTableH.getClass_name());
					map.put("class_id", classTableH.getId_());
					Integer bufindex=Integer.parseInt(index_arr[j]);
					map.put("course_id", course_id_list_arr[bufindex]);
					map.put("course_name", course_name_list_arr[bufindex]);
					list.add(map);
				}
			}
			returnmodel.setList(list);
			json = new Gson().toJson(returnmodel);
		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel isOKAndErrInfoDataModel = new IsOKAndErrInfoDataModel();
			isOKAndErrInfoDataModel.setErrinfo("参数错误");
			json = new Gson().toJson(isOKAndErrInfoDataModel);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
