package cn.sherven.doraemon.admin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 查看相关统计数据，只能查看！
 */
public class AStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpServletRequest request;
	public static HttpServletResponse response;
	private Map<String, Object> map = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AStatistics() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		this.request = request;
		this.response = response;
		map.put("isok", "ok");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void get() throws IOException {
		/**
		 * type:<person|department> 教师个人、系别
		 */
		String type = request.getParameter("type");
		if (type.equals("") == true || type == null) {
			map.put("isok", false);
			map.put("errinfo", "type null");
			response.getWriter().append(new Gson().toJson(map));
			return;
		}
		switch (type) {
		case "person":
			String id = request.getParameter("id");
			break;
		default:

		}
		response.getWriter().append(new Gson().toJson(map));
	}
}
