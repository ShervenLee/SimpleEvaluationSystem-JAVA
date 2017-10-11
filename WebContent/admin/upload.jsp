<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>西瓜APP-上传中心</title>
<meta name="description" content="这是一个文件上传页面">
<meta name="keywords" content="upload">
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
<link type="text/css" rel="stylesheet" href="css/upload.css">
</head>

<body data-type="generalComponents">
	<%@ include file="header.jsp"%>
	<div class="tpl-page-container tpl-page-header-fixed">
		<%@ include file="left.jsp"%>
		<div class="tpl-content-wrapper">
			<div class="tpl-content-page-title">通知栏发布中心</div>
			<ol class="am-breadcrumb">
				<li><a href="index.jsp" class="am-icon-home">首页</a></li>
				<li class="am-active">文件上传</li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span>上传中心
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

				<div class="upload_files">
					<form action="<%=Config.getHttprequestpath()%>UpdateTable"
						method="post" enctype="multipart/form-data">
						<div class="upload_content">
							<a class="files" href="javascript:;">文件上传<input type="file"
								name="upload" id="files_a" multiple />
							</a>
						</div>
						<input type="submit" value="开始上传" name="sbtn" class="btn" />
					</form>
					<div id="f_Name">
						<span></span>
					</div>
				</div>

				<script type="text/javascript">
					var uploadFiles = document.getElementById("files_a");
					var oDiv = document.getElementById("f_Name");
					var oContent = oDiv.getElementsByTagName("span")[0];
					uploadFiles.onchange = function() {
						oContent.innerHTML = uploadFiles.value;
					}
				</script>

			</div>


		</div>

	</div>


	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/js/upload.js"></script>
</body>

</html>