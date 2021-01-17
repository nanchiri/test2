
var vmUserContent = new Vue({
	el : '#contain',
	data : {
		userList : [],
		allUserCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search:"",
		flag:true
	},
	mounted : function() {//初始化时执行内容
		var self = this;
		$.get("UserManagementContent?page=1", function(data, status) {
			self.userList = data.userList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allUserCount = data.allUserCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {//点击分页时间
			this.$nextTick(function() {
				$.get("UserManagementContent?page=" + page, function(data, status) {
					console.log(data.userList)
					vmUserContent.userList = data.userList;
					vmUserContent.page = data.page;
					vmUserContent.prePage = data.prePage;
					vmUserContent.nextPage = data.nextPage;
					vmUserContent.pageNum = data.pageNum;
					vmUserContent.allUserCount = data.allUserCount;
					vmUserContent.search = "";
					vmUserContent.flag = true;
				});
			});
		},
		searchFunction:function(){//搜索分页时间
			this.$nextTick(function(){
				$.get("searchUser?page=1&search="+$(".user-search-bar").val(),function(data,status){
					console.log(data.userList);
					vmUserContent.userList = data.userList;
					vmUserContent.page = data.page;
					vmUserContent.prePage = data.prePage;
					vmUserContent.nextPage = data.nextPage;
					vmUserContent.pageNum = data.pageNum;
					vmUserContent.allUserCount = data.allUserCount;
					vmUserContent.search = data.search;
					vmUserContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){//点击搜索分页时间
			this.$nextTick(function(){
				$.get("searchUser?page="+page+"&search="+search,function(data,status){
					vmUserContent.userList = data.userList;
					vmUserContent.page = data.page;
					vmUserContent.prePage = data.prePage;
					vmUserContent.nextPage = data.nextPage;
					vmUserContent.pageNum = data.pageNum;
					vmUserContent.allUserCount = data.allUserCount;
					vmUserContent.search = data.search;
					vmUserContent.flag = false;
				})
			})
		}
	}
});

/*
 * 修改管理员密码表单校验
 */
$("#user-update-password-form").validator({
	rules : {
		passwordValidate : [ /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$/, "密码需要为字母数字混合,限制为6-14位" ],
	},
	fields : {
		'password' : "required;passwordValidate",
		'password-confirm':"required;passwordValidate;match(password)"
	},
	/**
	 * 表单验证通过后管理员密码更新提交按钮
	 * 提交表单
	 */
	valid : $("#user-update-password-input").click(function() {
		$.post("updateUserPassword", $("#user-update-password-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改密码成功!");
				$("#user-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/UserManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/**
 * 修改密码按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#user-alertPassword-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchUser?page=1&search=" + id;
	$.get(url, function(data, status){
		$("#user-update-password-form div #user-id").val(data.userList[0].id);
		$("#user-update-password-form #user-name").val(data.userList[0].account);
		$("#user-update-password-form #user-password").val("");
		$("#user-update-password-form #user-password-confirm").val("");
	})
})