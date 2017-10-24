package cn.sherven.doraemon.admin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.CheckStrType;
import cn.sherven.doraemon.admin.Model.MessageTableModel;
import cn.sherven.doraemon.hibernate.MessageTableH;

/**
 * Servlet implementation class AMsgTable
 */
public class AMsgTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> map = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AMsgTable() {
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
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		this.request = request;
		this.response = response;
		map.put("isok", "ok");

		String action = request.getParameter("action");

		if (action == null || action.equals("")) {
			get();
		} else {
			switch (action) {
			case "get":
				get();
				break;
			case "update":
				update();
				break;
			case "del":
				del();
				break;
			default:
				get();
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public Boolean del() throws IOException {
		String id = request.getParameter("id");
		if (id != null && id.equals("") == false) {
			if (MessageTableModel.del(id)) {
				map.put("isok", true);
			} else {
				map.put("isok", false);
				map.put("errinfo", "id null");
			}
		} else {
			map.put("isok", false);
			map.put("errinfo", "id null");
		}
		response.getWriter().append(new Gson().toJson(map));
		return false;

	}

	public Boolean update() {
		return false;
	}

	public Boolean add() {

		return false;
	}

	public void get() throws IOException {
		String page = request.getParameter("page");
		String id = request.getParameter("id");
		if (id != null && CheckStrType.isInt(id) == true) {
			MessageTableH model = MessageTableModel.getbyid(id);
			if (model==null) {
				map.put("isok", false);
				map.put("errinfo", "id not find");
				response.getWriter().append(new Gson().toJson(map));
				return;	
			}
			map.put("model", model);
			response.getWriter().append(new Gson().toJson(map));
			return;
		}
		if (!CheckStrType.isInt(page)) {
			page = "1";
			map.put("list", MessageTableModel.getbypage(Integer.parseInt(page)));
			map.put("maxpage", MessageTableModel.getMaxPageNub());
			map.put("currpage", page);
			response.getWriter().append(new Gson().toJson(map));
			return;
		}
		map.put("isok", "err");
		map.put("errinfo", "par err");
		response.getWriter().append(new Gson().toJson(map));

	}
}
