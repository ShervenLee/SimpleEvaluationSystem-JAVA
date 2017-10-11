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
import cn.sherven.doraemon.dataModel.Is_ok_listDataModel;
import cn.sherven.doraemon.hibernate.ClassTableH;
import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.ClassTableHC;
import cn.sherven.doraemon.hibernateController.CommentHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

/**
 * Servlet implementation class GetEvaTeaListDataModel
 */
public class GetEvaTeaListDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEvaTeaListDataModel() {
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
			UserStudentH queryBy_userid = UserStudent_HC.queryBy_userid(userid_);
			String class_id = queryBy_userid.getClass_id();
			ClassTableH classidmodel = ClassTableHC.queryBy_classTableID(class_id);

			String course_id_list = classidmodel.getCourse_id_list();
			String teacher_id_list = classidmodel.getTeacher_id_list();
			String teacher_name_list = classidmodel.getTeacher_name_list();
			String course_name_list = classidmodel.getCourse_name_list();

			String[] course_id_list_arr = course_id_list.split(",");
			String[] teacher_id_list_arr = teacher_id_list.split(",");
			String[] teacher_name_list_arr = teacher_name_list.split(",");
			String[] course_name_list_arr = course_name_list.split(",");

			List<CommentH> list = CommentHC.queryBy_STUID(userid_);
			if (list != null) {
				String buff;
				for (int i = 0; i < list.size(); i++) {
					buff = list.get(i).getCourses_id();
					for (int j = 0; j < course_id_list_arr.length; j++) {
						if (course_id_list_arr[j].equals(buff)) {
							course_id_list_arr[j] = "-1";
							teacher_id_list_arr[j] = "-1";
							teacher_name_list_arr[j] = "-1";
							course_name_list_arr[j] = "-1";
						}
					}
				}
			}

			List<String> course_id_list_arr_return = new ArrayList<String>();
			List<String> teacher_id_list_arr_return = new ArrayList<String>();
			List<String> teacher_name_list_arr_retun = new ArrayList<String>();
			List<String> course_name_list_arr_return = new ArrayList<String>();

			for (int i = 0; i < course_id_list_arr.length; i++) {
				if (!course_id_list_arr[i].equals("-1")) {
					course_id_list_arr_return.add(course_id_list_arr[i]);
					teacher_id_list_arr_return.add(teacher_id_list_arr[i]);
					teacher_name_list_arr_retun.add(teacher_name_list_arr[i]);
					course_name_list_arr_return.add(course_name_list_arr[i]);
				}
			}
			Is_ok_listDataModel listreturn = new Is_ok_listDataModel();
			List<Map<String, String>> listmodel = new ArrayList<Map<String, String>>();

			for (int i = 0; i < course_id_list_arr_return.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", teacher_name_list_arr_retun.get(i));
				map.put("course", course_name_list_arr_return.get(i));
				map.put("courseid", course_id_list_arr_return.get(i));
				map.put("teacherid", teacher_id_list_arr_return.get(i));
				listmodel.add(map);
			}

			listreturn.setList(listmodel);
			json = new Gson().toJson(listreturn);
			// json =
			// "{\"isok\":\"OK\",\"errinfo\":\"NO\",\"list\":[{\"name\":\"夏雨轩\",\"course\":\"流行音乐\",\"courseid\":\"0001\",\"teacherid\":\"100001\"},{\"name\":\"李飞\",\"course\":\"生物科学\",\"courseid\":\"0002\",\"teacherid\":\"100002\"}]}";

		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel isOKAndErrInfoDataModel = new IsOKAndErrInfoDataModel();
			isOKAndErrInfoDataModel.setErrinfo("参数错误");
			json = new Gson().toJson(isOKAndErrInfoDataModel);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
