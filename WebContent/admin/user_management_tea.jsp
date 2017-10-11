<%@page import="cn.sherven.doraemon.hibernateController.TeacherGroupHC"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="cn.sherven.doraemon.hibernate.UserTeacherH"%>
<%@page import="java.util.List"%>
<%@page import="cn.sherven.doraemon.admin.webmodel.UserManagementTea"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>西瓜APP后台管理中心-教师信息</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<link rel="stylesheet" href="assets/css/app.css">
</head>

<body data-type="generalComponents">

	<%@ include file="header.jsp"%>
	<%
		Integer pagenub = 1;
		String func;
		Integer maxpage;
		UserManagementTea model = new UserManagementTea(userid, key, "null");
		List<UserTeacherH> list = model.getList(pagenub);
		maxpage = model.getMaxpage();
	%>
	<div class="tpl-page-container tpl-page-header-fixed">
		<%@ include file="left.jsp"%>
		<div class="tpl-content-wrapper">
			<div class="tpl-content-page-title">教师人员列表</div>
			<ol class="am-breadcrumb">
				<li><a href="index.html" class="am-icon-home">首页</a></li>
				<li><a href="teachers-font-list.html">教师列表</a></li>
				<li class="am-active">教师表</li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span>数据列表
					</div>
					<div class="tpl-portlet-input tpl-fz-ml">
						<div class="portlet-input input-small input-inline">
							<div class="input-icon right">
								<i class="am-icon-search"></i> <input type="text"
									class="form-control form-control-solid" placeholder="搜索...">
							</div>
						</div>
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-6">
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<button type="button"
										class="am-btn am-btn-default am-btn-success">
										<span class="am-icon-plus"></span> 新增
									</button>
									<button type="button"
										class="am-btn am-btn-default am-btn-secondary">
										<span class="am-icon-save"></span> 保存
									</button>
									<button type="button"
										class="am-btn am-btn-default am-btn-warning">
										<span class="am-icon-archive"></span> 审核
									</button>
									<button type="button"
										class="am-btn am-btn-default am-btn-danger">
										<span class="am-icon-trash-o"></span> 删除
									</button>
								</div>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-form-group">
								<select data-am-selected="{btnSize: 'sm'}">
									<option value="option2">文学组</option>
									<option value="option3">高数组</option>
									<option value="option3">英语组</option>
									<option value="option3">计算机组</option>
								</select>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-input-group am-input-group-sm">
								<input type="text" class="am-form-field"> <span
									class="am-input-group-btn">
									<button
										class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
										type="button"></button>
								</span>
							</div>
						</div>
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table
									class="am-table am-table-striped am-table-hover table-main"
									style="text-align: center;">
									<thead>
										<tr>
											<th class="table-check"><input type="checkbox"
												class="tpl-table-fz-check"></th>
											<th class="table-id" style="text-align: center;">ID</th>
											<th class="table-title" style="text-align: center;">用户名</th>
											<th class="table-type" style="text-align: center;">所属组</th>
											<th class="table-author am-hide-sm-only"
												style="text-align: center;">联系电话</th>
											<th class="table-type" style="text-align: center;">所教班级ID</th>
											<th class="table-set" style="padding-left: 87px;">操作</th>
										</tr>
									</thead>
									<tbody>
										<%
											if (list != null) {

												for (UserTeacherH teamodel : list) {
										%>
										<tr>
											<td><input type="checkbox"></td>
											<td><%=teamodel.getTeacher_id()%></td>
											<td><a href="#"><%=teamodel.getName()%></a></td>
											<td><%=TeacherGroupHC.queryGroupNameBy_id(teamodel.getTeacher_group())%></td>
											<td class="am-hide-sm-only"><%=teamodel.getTel()%></td>
											<td class="am-hide-sm-only"><%=teamodel.getClass_id_list()%></td>
											<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<button
															class="am-btn am-btn-default am-btn-xs am-text-secondary">
															<span class="am-icon-pencil-square-o"></span> 编辑
														</button>
														<button
															class="am-btn am-btn-default am-btn-xs am-hide-sm-only">
															<span class="am-icon-copy"></span> 复制
														</button>
														<button
															class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
															<span class="am-icon-trash-o"></span> 删除
														</button>
													</div>
												</div>
											</td>
										</tr>
										<%
											}
											}
										%>

									</tbody>
								</table>
								<div class="am-cf">

									<div class="am-fr">
										<ul class="am-pagination tpl-pagination">
											<%
												if (maxpage >= 2&&pagenub!=1) {
											%>
											<li class="am-disabled"><a href="?pagenub=<%=pagenub + 1 %>">«</a></li>
											<%
												}
											%>

											<li class="am-active"><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">4</a></li>
											<li><a href="#">5</a></li>
											<%
												if (maxpage != pagenub && maxpage > pagenub) {
											%>
											<li><a href="?pagenub=<%=pagenub + 1 %>">»</a></li>
											<%
												}
											%>


										</ul>
									</div>
								</div>
								<hr>

							</form>
						</div>

					</div>
				</div>
				<div class="tpl-alert"></div>
			</div>










		</div>

	</div>


	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>

</html>