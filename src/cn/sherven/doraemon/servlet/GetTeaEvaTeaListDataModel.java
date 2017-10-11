package cn.sherven.doraemon.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.sherven.doraemon.Tool.Funtool;
import cn.sherven.doraemon.dataModel.IsOKAndErrInfoDataModel;
import cn.sherven.doraemon.dataModel.Is_ok_listDataModel;
import cn.sherven.doraemon.hibernate.CommentTeacherH;
import cn.sherven.doraemon.hibernate.TeacherGroupH;
import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.CommentTeacherHC;
import cn.sherven.doraemon.hibernateController.TeacherGroupHC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;
import jdk.nashorn.internal.ir.Flags;

/**
 * Servlet implementation class GetTeaEvaTeaListDataModel
 */
public class GetTeaEvaTeaListDataModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTeaEvaTeaListDataModel() {
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
			json = new Gson().toJson(new IsOKAndErrInfoDataModel());
		} else if (Funtool.isKeyOK(userid_, key_)) {
			List<UserTeacherH> modellist = UserTeacher_HC.queryBy_groupid(userid_);
			List<CommentTeacherH> srclist = CommentTeacherHC.queryBy_teacher_id_src(userid_);
			if (modellist == null) {
				// json="{\"isok\":\"NO\",\"errinfo\":\"NO\",\"list\":[{\"name\":\"王力\",\"course\":\"经济与管理\",\"id\":\"312015590101111\",\"content\":\"经济界的比尔盖茨\"},{\"name\":\"夏雨轩\",\"course\":\"流行音乐\",\"id\":\"312015590101112\",\"content\":\"音乐界的周杰伦\"},{\"name\":\"李飞\",\"course\":\"生物科学\",\"id\":\"312015590101113\",\"content\":\"教学严谨\"},{\"name\":\"赵磊\",\"course\":\"经济与管理\",\"id\":\"312015590101114\",\"content\":\"经济界的比尔盖茨\"}]}";
				json = new Gson().toJson(new IsOKAndErrInfoDataModel());
			} else {
				Is_ok_listDataModel model = new Is_ok_listDataModel();
				for (UserTeacherH userTeacherH : modellist) {
					if (userTeacherH.getTeacher_id().equals(userid_)) {
						continue;// 如果是自己则跳过
					}
					// 排除已评论教师
					boolean isok = true;
					for (CommentTeacherH commentTeacherH : srclist) {
						if (userTeacherH.getTeacher_id().equals(commentTeacherH.getTeacher_id_target())) {
							isok = false;
							break;
						}
					}

					if (isok) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", userTeacherH.getName());
						map.put("course", "");// 本来预留给课程的，但是一个老师不止一门课程，故此只显示教师名字，没有更改安卓端，所以暂留数据
						map.put("id", userTeacherH.getTeacher_id());
						map.put("content", "");// 暂时不用的参数，原本用在教师签名之类的
						model.addMap(map);
					}
					isok = true;
				}
				json = new Gson().toJson(model);
			}

		} else {
			// 参数错误，APP跳转到登录界面信息
		}
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

}
