<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 引入css样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/Commons.css">

<!-- 引入JS文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/GoodsManagement.js"></script>

<script type="text/javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
</head>

<body>
	<div class="com-top">
		<div class="com-title">
			<h2>商品管理</h2>
		</div>
		<div class="goods-functionArea" style="padding-bottom: 10px;">
			<div class="com-search input-group-lg">
				<input type="text" class="goods-search-bar" name="search"
					placeholder="请输入商品ID或者商品名">
				<button class="btn btn-secondary goods-search-button"
					id="goods-search-button" @click="searchFunction()">搜索</button>
			</div>
			<div>
				<button class="btn btn-primary goods-addgoodsdent"
					data-toggle="modal" data-target="#goods-add" id="goods-add-button">添加商品</button>
			</div>

		</div>
	</div>
	<!-- 商品列表 -->
	<div class="content" id='content'>
		<table class="table table-striped table-hover" id="table-content">
			<thead>
				<tr>
					<th>商品ID</th>
					<th>所属分类</th>
					<th>商品名称</th>
					<th>价格</th>
					<th>数量</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="goods in goodsList">
					<td>{{goods.id}}</td>
					<td>{{goods.typeName}}</td>
					<td>{{goods.name}}</td>
					<td>{{goods.price}}</td>
					<td>{{goods.amount}}</td>
					<td><button class='btn btn-primary goods-alert'
							v-bind:value="goods.id" data-toggle='modal'
							data-target='#goods-update' id='goods-update-button'>修改</button>
						<button id='goods-delete-button'
							class='btn btn-danger goods-delete' v-bind:value="goods.id">删除</button></td>
				</tr>
			</tbody>
		</table>
		<div class="com-total" id="goods-total">共有{{allGoodsCount}}条信息</div>
		<!-- 分页功能 -->
		<div class="pageNav">
			<ul class="pagination">
				<li class='page-item'><a class='page-link' id='page' href="#"
					@click="pageChange(prePage)" v-if="flag == true">上一页</a></li>
				<li class='page-item'><a class='page-link' id='page' href="#"
					@click="searchPage(prePage,search)" v-if="flag == false">上一页</a></li>
				<li class='page-item active' v-for="pages in pageNum"
					v-if="pages==page"><a class='page-link' id='page'
					@click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a> <a
					class='page-link' id='page' @click="searchPage(pages,search)"
					href="#" v-else>{{pages}}</a></li>
				<li class='page-item' v-else><a class='page-link' id='page'
					@click="pageChange(pages)" href="#" v-if="flag">{{pages}}</a> <a
					class='page-link' id='page' @click="searchPage(pages,search)"
					href="#" v-else>{{pages}}</a></li>

				<li class='page-item'><a class='page-link' id='page' href="#"
					@click="pageChange(nextPage)" v-if="flag == true">下一页</a></li>
				<li class='page-item'><a class='page-link' id='page' href="#"
					@click="searchPage(nextPage,search)" v-if="flag == false">下一页</a></li>
			</ul>
		</div>
	</div>

	<!-- 添加模态框 -->
	<div class="modal fade" id="goods-add">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">添加商品</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="goods-add-form" method="post"
						enctype="multipart/form-data">
						<div>
							<label for="goods-type" style="width: 100px;">所属分类:</label> <select
								id="goods-type" name="type_id">
								<option value="">---请选择---</option>
							</select>
						</div>
						<div>
							<label for="goods-password" style="width: 100px;">商品名称:</label><input
								type="text" name="name" id="goods-name">
						</div>
						<div>
							<label for="goods-price" style="width: 100px;">商品价格:</label><input
								type="text" name="price" id="goods-price">
						</div>
						<div>
							<label for="goods-show" style="width: 100px;">显示界面:</label> <select
								id="goods-show" name="isShow">
								<option value="">---请选择---</option>
								<option value="0">不显示</option>
								<option value="1">Section1</option>
								<option value="2">Section2</option>
							</select>
						</div>
						<div id="goods-img">
							<label for="goods-img" style="width: 100px;">商品图片1:</label><input
								type="file" name="img1" />
						</div>
						<div>
							<label for="goods-amount" style="width: 100px;">商品数量:</label><input
								type="text" name="amount" id="goods-amount">
						</div>
					</form>
				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="goods-add-img">添加图片</button>
					<button class="btn btn-primary" id="goods-add-input">提交</button>
				</div>

			</div>
		</div>
	</div>

	<!-- 修改模态框 -->
	<div class="modal fade" id="goods-update">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title">商品修改</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body">
					<form action="" id="goods-update-form" method="post">
						<div>
							<label for="goods-name" style="width: 100px;">商品ID:</label><input
								type="text" name="id" id="goods-id" readonly="readonly">
						</div>
						<div>
							<label for="goods-type" style="width: 100px;">所属分类:</label> <select
								id="goods-type" name="type_id">
								<option value="">---请选择---</option>
							</select>
						</div>
						<div>
							<label for="goods-password" style="width: 100px;">商品名称:</label><input
								type="text" name="name" id="goods-name">
						</div>
						<div>
							<label for="goods-password" style="width: 100px;">商品价格:</label><input
								type="text" name="price" id="goods-price">
						</div>
						<div>
							<label for="goods-show" style="width: 100px;">显示界面:</label> <select
								id="goods-show" name="isShow">
								<option value="">---请选择---</option>
								<option value="0">不显示</option>
								<option value="1">Section1</option>
								<option value="2">Section2</option>
							</select>
						</div>
						<div id="goods-img">
							<label for="goods-img" style="width: 100px;">商品图片1:</label><input
								type="file" name="img1" />
						</div>
						<div>
							<label for="goods-amount" style="width: 100px;">商品数量:</label><input
								type="text" name="amount" id="goods-amount">
						</div>
					</form>

				</div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button class="btn btn-primary" id="goods-update-img">添加图片</button>
					<button class="btn btn-primary" id="goods-update-input">修改</button>
				</div>

			</div>
		</div>
	</div>


</body>
</html>
