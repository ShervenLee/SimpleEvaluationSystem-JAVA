package cn.sherven.doraemon.admin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.sherven.doraemon.hibernateController.ClassTableHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;


/**
 * Servlet implementation class AIndex
 */
public class AIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
private Map<String, Object> map=new HashMap<>();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AIndex() {
        super();
        map.put("isok", "ok");
        map.put("errinfo", "null");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		map.put("stunub", UserStudent_HC.getMaxNub());
		map.put("teanub", UserTeacher_HC.getMaxNub());
		map.put("classnub", ClassTableHC.getMaxNub());
		response.getWriter().append(new Gson().toJson(map));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
