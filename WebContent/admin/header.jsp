<%@page import="cn.sherven.doraemon.dataModel.AdminInfoDataModel"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="cn.sherven.doraemon.admin.http.HttpRequest"%>
<%@page import="cn.sherven.doraemon.Tool.Config"%>
<%@page import="cn.sherven.doraemon.Tool.Funtool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//验证是否登陆
	if (!(isCookieName("userid", request) && isCookieName("key", request))) {
		//没有登录则重定向到登录页面
		response.setHeader("refresh", "0,url=login.jsp");
		return;
	}
	if (!Funtool.isKeyOK(Funtool.getCookieValue(request.getCookies(), "userid"),
			Funtool.getCookieValue(request.getCookies(), "key"))) {
		//用户ID与key不匹配
		response.setHeader("refresh", "0,url=login.jsp");
		return;
	}
	String username_header = "111";

	String userid = Funtool.getCookieValue(request.getCookies(), "userid");
	String key = Funtool.getCookieValue(request.getCookies(), "key");

	{
		String url = Config.httpRequestPath + "/GetUserInfo";
		String param = "userid=" + userid + "&key=" + key;
		String s = HttpRequest.sendPost(url, param);
		Gson gson = new Gson();
		AdminInfoDataModel model = gson.fromJson(s, AdminInfoDataModel.class);
		username_header = model.getName();
	}
%>
<%!public boolean isCookieName(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0) {
			return false;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return true;
			}
		}
		return false;
	}%>
<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<a href="javascript:;" class="tpl-logo"> <img
			src="assets/img/logo.png" alt="">
		</a>
	</div>
	<div
		class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

	</div>
	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: '#topbar-collapse'}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>

	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
			<!--提醒部分
			 <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
				<a class="am-dropdown-toggle tpl-header-list-link"
				href="javascript:;"> <span class="am-icon-bell-o"></span> 提醒 <span
					class="am-badge tpl-badge-success am-round">n</span></span>
			</a>
				<ul class="am-dropdown-content tpl-dropdown-content">
					<li class="tpl-dropdown-content-external">
						<h3>
							你有 <span class="tpl-color-success">n</span> 条提醒
						</h3>
						<a href="###">全部</a>
					</li>
					<li class="tpl-dropdown-list-bdbc"><a href="#"
						class="tpl-dropdown-list-fl"><span
							class="am-icon-btn am-icon-plus tpl-dropdown-ico-btn-size tpl-badge-success"></span>某某学员成功加入西瓜APP</a>
						<span class="tpl-dropdown-list-fr">3小时前</span></li>
					<li class="tpl-dropdown-list-bdbc"><a href="#"
						class="tpl-dropdown-list-fl"><span
							class="am-icon-btn am-icon-check tpl-dropdown-ico-btn-size tpl-badge-danger"></span>某某老师已成功加入</a>
						<span class="tpl-dropdown-list-fr">15分钟前</span></li>
					<li class="tpl-dropdown-list-bdbc"><a href="#"
						class="tpl-dropdown-list-fl"><span
							class="am-icon-btn am-icon-bell-o tpl-dropdown-ico-btn-size tpl-badge-warning"></span>某班某学员已成功提交教师评测列表</a>
						<span class="tpl-dropdown-list-fr">2天前</span></li>
				</ul>
			</li> -->
			<!--消息部分
			 <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
				<a class="am-dropdown-toggle tpl-header-list-link"
				href="javascript:;"> <span class="am-icon-comment-o"></span> 消息
					<span class="am-badge tpl-badge-danger am-round">9</span></span>
			</a>
				<ul class="am-dropdown-content tpl-dropdown-content">
					<li class="tpl-dropdown-content-external">
						<h3>
							你有 <span class="tpl-color-danger">n</span> 条新消息
						</h3>
						<a href="###">全部</a>
					</li>
					<li><a href="#" class="tpl-dropdown-content-message"> <span
							class="tpl-dropdown-content-photo"> <img
								src="assets/img/user02.png" alt="">
						</span> <span class="tpl-dropdown-content-subject"> <span
								class="tpl-dropdown-content-from"> admin </span> <span
								class="tpl-dropdown-content-time">10分钟前 </span>
						</span> <span class="tpl-dropdown-content-font">西瓜 APP 的诞生，依托于
								GitHub 及其他技术社区上一些优秀的资源；西瓜 APP 的成长，则离不开用户的支持。 </span>
					</a> <a href="#" class="tpl-dropdown-content-message"> <span
							class="tpl-dropdown-content-photo"> <img
								src="assets/img/user03.png" alt="">
						</span> <span class="tpl-dropdown-content-subject"> <span
								class="tpl-dropdown-content-from"> Code </span> <span
								class="tpl-dropdown-content-time">18分钟前</span>
						</span> <span class="tpl-dropdown-content-font"> 登陆异常，请及时处理 </span>
					</a></li>

				</ul>
			</li> -->
			<!--进度部分
			 <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
				<a class="am-dropdown-toggle tpl-header-list-link"
				href="javascript:;"> <span class="am-icon-calendar"></span> 进度 <span
					class="am-badge tpl-badge-primary am-round">n</span></span>
			</a>
				<ul class="am-dropdown-content tpl-dropdown-content">
					<li class="tpl-dropdown-content-external">
						<h3>
							你有 <span class="tpl-color-primary">n</span> 个任务进度
						</h3>
						<a href="###">全部</a>
					</li>
					<li><a href="javascript:;"
						class="tpl-dropdown-content-progress"> <span class="task">
								<span class="desc">西瓜 APP 管理中心 v0.0.0 </span> <span
								class="percent">45%</span>
						</span> <span class="progress">
								<div class="am-progress tpl-progress am-progress-striped">
									<div class="am-progress-bar am-progress-bar-success"
										style="width: 45%"></div>
								</div>
						</span>
					</a></li>
					<li><a href="javascript:;"
						class="tpl-dropdown-content-progress"> <span class="task">
								<span class="desc">新闻内容页 </span> <span class="percent">30%</span>
						</span> <span class="progress">
								<div class="am-progress tpl-progress am-progress-striped">
									<div class="am-progress-bar am-progress-bar-secondary"
										style="width: 30%"></div>
								</div>
						</span>
					</a></li>
					<li><a href="javascript:;"
						class="tpl-dropdown-content-progress"> <span class="task">
								<span class="desc">管理中心 </span> <span class="percent">60%</span>
						</span> <span class="progress">
								<div class="am-progress tpl-progress am-progress-striped">
									<div class="am-progress-bar am-progress-bar-warning"
										style="width: 60%"></div>
								</div>
						</span>
					</a></li>

				</ul>
			</li> -->
			<li class="am-hide-sm-only"><a href="javascript:;"
				id="admin-fullscreen" class="tpl-header-list-link"><span
					class="am-icon-arrows-alt"></span> <span class="admin-fullText">全屏模式</span></a></li>

			<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
				<a class="am-dropdown-toggle tpl-header-list-link"
				href="javascript:;"> <span class="tpl-header-list-user-nick"><%=username_header%></span><span
					class="tpl-header-list-user-ico"> <img
						src="assets/img/user01.png"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>
					<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
					<li><a href="logout.jsp"><span class="am-icon-power-off"></span>
							退出</a></li>
				</ul>
			</li>
			<li><a href="logout.jsp" class="tpl-header-list-link"><span
					class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
		</ul>
	</div>
</header>