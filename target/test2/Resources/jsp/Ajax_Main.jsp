<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>伊藤家后台管理系统</title>

<!-- 新 Bootstrap4 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

<!-- 引入本页的js文件 -->
<script
	src="${pageContext.request.contextPath }/Resources/js/Ajax_Main.js"></script>

<!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>

<!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<!-- 引入Vue -->
<script src="https://cdn.jsdelivr.net/npm/vue"></script>

<!-- 导入前端数据校验插件 -->
<script
	src="https://cdn.staticfile.org/nice-validator/1.1.4/jquery.validator.min.js?local=zh-CN"></script>

<!-- 引入css文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/Resources/css/Ajax_Main.css">



</head>

<body>
	<div class="top">
		<h2>欢迎使用伊藤家后台管理系统</h2>
		<input type="hidden" value="${pageContext.request.contextPath}"
			id="path">
		<input type="hidden" value=${sessionScope.permission}
			id="permission">
		<input type="hidden" value=${sessionScope.typeManager}
			id="typeManager">
		<input type="hidden" value=${sessionScope.goodsManager}
			id="goodsManager">
		<input type="hidden" value=${sessionScope.orderManager}
			id="orderManager">
		<input type="hidden" value=${sessionScope.userManager}
			id="userManager">
	</div>
	<div class="bottom">
		<div class="index">
			<ul class="nav nav-pills flex-column nav-justified">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/AdministratorManagement" id="adminManagement">权限管理</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/TypeManagement" id="typeManagement">分类管理</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/GoodsManagement" id="goodsManagement">商品管理</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/OrderManagement" id="orderManagement">订单管理</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/UserManagement" id="userManagement">用户管理</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/AdministratorLogOut" value="logout">退出登录</a></li>
			</ul>
		</div>
	<div class="contain" id="contain"></div>
	</div>

</body>
</html>
