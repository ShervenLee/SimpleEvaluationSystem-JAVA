<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tpl-left-nav tpl-left-nav-hover">
	<div class="tpl-left-nav-title">西瓜 APP 列表</div>
	<div class="tpl-left-nav-list">
		<ul class="tpl-left-nav-menu">
			<li class="tpl-left-nav-item"><a href="index.html"
				class="nav-link active"> <i class="am-icon-home"></i> <span>首页</span>
			</a></li>
			<li class="tpl-left-nav-item"><a href="javascript:;"
				class="nav-link tpl-left-nav-link-list"> <i
					class="am-icon-table"></i> <span>用户管理</span> <i
					class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
			</a>
				<ul class="tpl-left-nav-sub-menu">
					<li><a href="user_management_tea.jsp"> <i
							class="am-icon-angle-right"></i> <span>教师</span> <i
							class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
					</a> <a href="user_management_stu.jsp"> <i
							class="am-icon-angle-right"></i> <span>学生</span> <i
							class="tpl-left-nav-content tpl-badge-success">n</i>
					</a> <a href="user_admin.html"> <i class="am-icon-angle-right"></i>
							<span>管理员</span>
					</a> <a href="classtable.html"> <i class="am-icon-angle-right"></i>
							<span>课程表</span>
					</a></li>
				</ul></li>

			<li class="tpl-left-nav-item"><a href="javascript:;"
				class="nav-link tpl-left-nav-link-list"> <i
					class="am-icon-wpforms"></i> <span>admin个人信息管理</span> <i
					class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
			</a>
				<ul class="tpl-left-nav-sub-menu" style="display: none;">
					<li><a href="form-admin.html"> <i
							class="am-icon-angle-right"></i> <span>admin个人信息修改</span> <i
							class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
					</a> <a href="form-line.html"> <i class="am-icon-angle-right"></i>
							<span>通知发布栏</span>
					</a> <a href="user_admin.html"> <i class="am-icon-angle-right"></i>
							<span>Admin</span>
					</a></li>
				</ul></li>
			<li class="tpl-left-nav-item"><a href="upload.jsp"
				class="nav-link tpl-left-nav-link-list"> <i class="am-icon-key"></i>
					<span>数据库文件上传</span>

			</a></li>
		</ul>
	</div>
</div>