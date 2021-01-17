<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/Commons.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/UserManagement.js"></script>
</head>

<body>
	<div class="com-top">
		<div class="com-title">
			<h2>用户管理</h2>
		</div>
		<div class="user-functionArea">
			<div class="com-search input-group-lg">
				<input type="text" class="user-search-bar" name="search"
					placeholder="请输入ID或者账号">
				<button class="btn btn-secondary user-search-button"
					id="user-search-button" @click="searchFunction()">搜索</button>
			</div>
		</div>
	</div>
	<!-- 用户列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>编号ID</th>
					<th>账号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="user in userList">
					<td>{{user.id}}</td>
					<td>{{user.account}}</td>
					<td>
					<button type="button" id='user-alertPassword-button' class="btn btn-secondary user-alertPassword-button" v-bind:value="user.id" data-toggle='modal' data-target='#user-update-password'>修改密码</button>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="com-total" id="user-total">共有{{allUserCount}}条信息</div>
		<!-- 分页功能 -->
		<div class="pageNav">
			<ul class="pagination">
				<li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(prePage)" v-if="flag == true">上一页</a></li>
				<li class='page-item'><a class='page-link' id='page' href="#" @click="searchPage(prePage,search)" v-if="flag == false">上一页</a></li>
				<li class='page-item active' v-for="pages in pageNum" v-if="pages==page">
					<a class='page-link' id='page' @click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a>
					<a class='page-link' id='page' @click="searchPage(pages,search)" href="#" v-else>{{pages}}</a>
				</li>
				<li class='page-item' v-else>
					<a class='page-link' id='page' @click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a>
					<a class='page-link' id='page' @click="searchPage(pages,search)" href="#" v-else>{{pages}}</a>
				</li>
				
				<li class='page-item'><a class='page-link' id='page' href="#" @click="pageChange(nextPage)" v-if="flag == true">下一页</a></li>
				<li class='page-item'><a class='page-link' id='page' href="#" @click="searchPage(nextPage,search)" v-if="flag == false">下一页</a></li>
			</ul>
		</div>
	</div>
	
	<!-- 用户修改密码模态框 -->
	<div class="modal fade" id="user-update-password">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">更新用户密码</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="user-update-password-form" method="post">
						<div>
							<label for="user-id" style="width:100px;">用户ID:</label><input type="text" name="id"
								id="user-id" readonly="readonly">
						</div>
						<div>
							<label for="user-name" style="width:100px;">用户姓名:</label><input type="text"
								name="user" id="user-name" readonly="readonly">
						</div>
						<div>
							<label for="user-password" style="width:100px;">用户密码:</label><input type="password" name="password" id="user-password"> 
						</div>
						<div><label for="user-password-confirm" style="width:100px;">再次确认密码:</label><input type="password" name="password-confirm" id="user-password-confirm"></div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="user-update-password-input">修改</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
