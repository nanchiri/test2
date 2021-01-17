<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>分类管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/Commons.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/TypeManagement.js"></script>
</head>

<body>
	<div class="com-top">
		<div class="com-title">
			<h2>分类管理</h2>
		</div>
		<div class="type-functionArea" style="padding-bottom:10px;">
			<div class="com-search input-group-lg">
				<input type="text" id="type-search-bar"  class="type-search-bar" name="search"
					placeholder="请输入分类ID或分类名">
				<button class="btn btn-secondary type-search-button"
					id="type-search-button" @click="searchFunction()">搜索</button>
			</div>
			<div>
				<button class="btn btn-primary type-addType"
					data-toggle="modal" data-target="#type-add" id="type-add-button">添加分类</button>
			</div>
		</div>
	</div>
	<!-- 分类列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>分类ID</th>
					<th>分类名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="type in typeList">
					<td>{{type.id}}</td>
					<td>{{type.name}}</td>
					<td><button class='btn btn-primary com-alert' v-bind:value="type.id" data-toggle='modal' data-target='#type-update' id='type-update-button'>修改</button>
					<button id='type-delete-button' class='btn btn-danger type-delete' v-bind:value="type.id">删除</button></td>
				</tr>
			</tbody>
		</table>
		<div class="com-total" id="type-total">共有{{allTypeCount}}条信息</div>
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

	<!-- 分类添加模态框 -->
	<div class="modal fade" id="type-add">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">添加分类</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="type-add-form" method="post">
						<div>
							<label for="type-password" style="width:100px;">分类名称:</label><input type="text"
								name="name" id="type-name">
						</div>
					</form>
				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="type-add-input">提交</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 分类修改模态框 -->
	<div class="modal fade" id="type-update">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">修改分类</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="type-update-form" method="post">
						<div>
							<label for="type-name" style="width:100px;">分类ID:</label><input type="text"
								name="id" id="type-id" readonly="readonly">
						</div>
						<div>
							<label for="type-password" style="width:100px;">分类名称:</label><input type="text"
								name="name" id="type-name">
						</div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="type-update-input">修改</button>
				</div>

			</div>
		</div>
	</div>
	
	
</body>
</html>
