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
import cn.sherven.doraemon.dataModel.ScheduleDataModel;
import cn.sherven.doraemon.hibernate.ClassTableH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.ClassTableHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;
import javassist.compiler.ast.NewExpr;

/**
 * Servlet implementation class GetScheduleDataModel
 */
public class GetScheduleDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetScheduleDataModel() {
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
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("参数不完整");
			model.setIsok("OK");
			json = new Gson().toJson(model);
		} else if (Funtool.isKeyOK(userid_, key_)) {
			// json=
			// "{\"isok\":\"OK\",\"errinfo\":\"NO\",\"list\":[{\"0\":[\"课程0\",\"老师0\"]},{\"1\":[\"课程1\",\"老师1\"]},{\"2\":[\"课程2\",\"老师2\"]}]}";

			UserStudentH model1 = UserStudent_HC.queryBy_userid(userid_);
			String classid = model1.getClass_id();
			ClassTableH model2 = ClassTableHC.queryBy_classTableID(classid);
			String teacher_name_list = model2.getTeacher_name_list();
			String course_name_list = model2.getCourse_name_list();

			String[] teacher_name_arr = teacher_name_list.split(",");
			String[] course_name_arr = course_name_list.split(",");

			if (teacher_name_arr.length != course_name_arr.length) {
				// 长度必须一致，数据库存储是有顺序对应关系
				System.out.println("错误");
				return;
			}
			for (int i = 0; i < course_name_arr.length; i++) {
				System.out.println(course_name_arr[i]);
			}
			for (int i = 0; i < teacher_name_arr.length; i++) {
				System.out.println(teacher_name_arr[i]);
			}
			
			
			ScheduleDataModel model = new ScheduleDataModel();
			
			List<Map<String, String[]>> list=new ArrayList<Map<String, String[]>>();
			
			for (Integer i = 0; i < teacher_name_arr.length; i++) {
					Map<String, String[]> map=new HashMap<String,String[]>();
					String[] buff={course_name_arr[i],teacher_name_arr[i]};
					map.put(i.toString(), buff);
					list.add(map);
			}
			model.setList(list);
			
			json=new Gson().toJson(model);
			
		} else {
			// 参数错误，APP跳转到登录界面信息
			IsOKAndErrInfoDataModel model = new IsOKAndErrInfoDataModel();
			model.setErrinfo("null");
			model.setIsok("OK");
			json = new Gson().toJson(model);
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
