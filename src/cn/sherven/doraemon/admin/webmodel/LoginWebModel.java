package cn.sherven.doraemon.admin.webmodel;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;

import cn.sherven.doraemon.Tool.Config;
import cn.sherven.doraemon.admin.http.HttpRequest;
import cn.sherven.doraemon.dataModel.LoginDataModel;

public class LoginWebModel {
	private LoginDataModel model;

	private String url = "";
	private String userid = "";
	private String password = "";

	public LoginWebModel(HttpServletRequest request) {
		url = Config.getHttprequestpath() + "Login";
		userid = request.getParameter("userid");
		password = request.getParameter("password");

		String param = "userid=" + userid + "&password=" + password;
		String s = HttpRequest.sendPost(url, param);
		Gson gson = new Gson();
		try {
			model = gson.fromJson(s, LoginDataModel.class);
		} catch (Exception e) {
			model = new LoginDataModel();
			model.setIsok("NO");
			model.setErrinfo("ToJsonErr");
		}

	}

	/**
	 * @return the model
	 */
	public LoginDataModel getModel() {
		return model;
	}
}
