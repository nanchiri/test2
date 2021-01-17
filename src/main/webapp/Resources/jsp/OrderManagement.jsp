<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/Commons.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/OrderManagement.js"></script>
</head>

<body>
	<div class="com-top">
		<div class="com-title">
			<h2>订单管理</h2>
		</div>
		<div class="order-functionArea">
			<div class="com-search input-group-lg">
				<input type="text" id="order-search-bar"  class="order-search-bar" name="search"
					placeholder="请输入订单ID">
				<button class="btn btn-secondary order-search-button"
					id="order-search-button" @click="searchFunction()">搜索</button>
			</div>
		</div>
	</div>
	<!-- 订单列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>订单ID</th>
					<th>用户</th>
					<th>总金额</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="order in orderList">
					<td>{{order.id}}</td>
					<td>{{order.userName}}</td>
					<td>{{order.amount}}</td>
					<td>
						<button class='btn btn-primary com-alert' v-bind:value="order.id" data-toggle='modal' data-target='#order-update' id='order-update-button'>修改</button>
						<!-- <button class='btn btn-primary com-alert' v-bind:value="order.id" data-toggle='modal' data-target='#order-update' id='order-update-button'>收货地址</button> -->
					</td>
				</tr>
			</tbody>
		</table>
		<div class="com-total" id="order-total">共有{{allOrderCount}}条信息</div>
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

	<!-- 订单修改模态框 -->
	<div class="modal fade" id="order-update">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">修改订单</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="order-update-form" method="post">
						<div>
							<label for="order-name">订单ID:</label><input type="text"
								name="id" id="order-id" readonly="readonly">
						</div>
						<div>
							<label for="order-password">订单金额:</label><input type="text"
								name="amount" id="order-amount">
						</div>
					</form>
				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="order-update-input">修改</button>
				</div>

			</div>
		</div>
	</div>
	
	
</body>
</html>
