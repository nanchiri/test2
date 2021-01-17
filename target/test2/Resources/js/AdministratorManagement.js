
var vmAdminContent = new Vue({
	el : '#contain',
	data : {
		adminList : [],
		allAdminCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search:"",
		flag:true
	},
	mounted : function() {//初始化时执行内容
		var self = this;
		$.get("AdministratorManagementContent?page=1", function(data, status) {
			self.adminList = data.adminList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allAdminCount = data.allAdminCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {//点击分页时间
			this.$nextTick(function() {
				$.get("AdministratorManagementContent?page=" + page, function(data, status) {
					console.log(data.adminList)
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allAdminCount = data.allAdminCount;
					vmAdminContent.search = "";
					vmAdminContent.flag = true;
				});
			});
		},
		searchFunction:function(){//搜索分页时间
			this.$nextTick(function(){
				$.get("searchAdministraotr?page=1&search="+$(".admin-search-bar").val(),function(data,status){
					console.log(data.adminList);
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allAdminCount = data.allAdminCount;
					vmAdminContent.search = data.search;
					vmAdminContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){//点击搜索分页时间
			this.$nextTick(function(){
				$.get("searchAdministraotr?page="+page+"&search="+search,function(data,status){
					vmAdminContent.adminList = data.adminList;
					vmAdminContent.page = data.page;
					vmAdminContent.prePage = data.prePage;
					vmAdminContent.nextPage = data.nextPage;
					vmAdminContent.pageNum = data.pageNum;
					vmAdminContent.allAdminCount = data.allAdminCount;
					vmAdminContent.search = data.search;
					vmAdminContent.flag = false;
				})
			})
		},
		changePer_1:function(){
			var date = document.getElementsByName("permission");
		    var str = "";
		    for(var i = 0;i < date.length;i++) {
		        if (date[i].checked == true) {
		            if(i == 0){
		            	str += date[i].value;
		            }else{
		            	str += "," + date[i].value;
		            }
		        }
		    }
		    $("#admin-add-form #admin-per").val(str);
		},
		changePer_2:function(){
			var date = document.getElementsByName("permission");
		    var str = "";
		    for(var i = 0;i < date.length;i++) {
		        if (date[i].checked == true) {
		            if(i == 0){
		            	str += date[i].value;
		            }else{
		            	str += "," + date[i].value;
		            }
		        }
		    }
		    $("#admin-update-form #admin-per").val(str);
		}
	}
});

/*
 * 添加管理员功能表单校验
 */
$("#admin-add-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "管理员姓名必须是中文或者英文" ],
		passwordValidate : [ /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$/, "密码需要为字母数字混合,限制为6-14位" ],
	},
	fields : {
		'user' : "required;nameValidate;remote(" + $("#path").val() + "/AdministratorNameIsExist)",
		'password' : "required;passwordValidate",
		'permission' : "checked(1~)",
		'data' : ""
	},
	/**
	 * 新增管理员表单的提交按钮点击事件
	 * 提交表单
	 */
	valid : $("#admin-add-input").click(function() {
		$.post("addAdministrator", $("#admin-add-form").serialize(), function(data, status) {
			if (status == "success") {
				alert(data.result);
				if(data.result == "添加成功!"){
					$("#admin-add").modal('hide');
					$(".modal-backdrop").remove();
					var path = $("#path").val();
					$('#contain').load(path + "/AdministratorManagement");
				}
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/*
 * 修改管理员权限表单校验
 */
$("#admin-update-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "管理员姓名必须是中文或者英文" ],
	},
	fields : {
		'user' : "required;nameValidate;remote(" + $("#path").val() + "/AdministratorNameIsExist)",
		'permission' : "checked(1~)",
		'data' : ""
	},

	/**
	 * 表单验证通过后管理员更新
	 */
	valid : $("#admin-update-input").click(function() {
		$.post("updateAdministrator", $("#admin-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert(data.result);
				if(data.result == "修改成功!"){
					$("#admin-add").modal('hide');
					$(".modal-backdrop").remove();
					var path = $("#path").val();
					$('#contain').load(path + "/AdministratorManagement");
				}
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});


/*
 * 修改管理员密码表单校验
 */
$("#admin-update-password-form").validator({
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
	valid : $("#admin-update-password-input").click(function() {
		$.post("updateAdministratorPassword", $("#admin-update-password-form").serialize(), function(data, status) {
			if (status == "success") {
				alert("修改密码成功!");
				$("#admin-add").modal('hide');
				$(".modal-backdrop").remove();
				var path = $("#path").val();
				$('#contain').load(path + "/AdministratorManagement");
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/**
 * 点击添加管理员按钮事件
 * 清空表单内容
 */
$("#admin-add-button").click(function(){
	$("#admin-add-form div #admin-name").val("");
	$("#admin-add-form div #admin-password").val("");
	$("#admin-add-form input:checkbox:eq('0')").attr("checked", true);
	$("#admin-add-form input:checkbox:eq('1')").attr("checked", false);
	$("#admin-add-form input:checkbox:eq('2')").attr("checked", false);
	$("#admin-add-form input:checkbox:eq('3')").attr("checked", false);
});


/**
 * 修改按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#admin-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	$("#admin-update-form input:checkbox:eq('0')").attr("checked", false);
	$("#admin-update-form input:checkbox:eq('1')").attr("checked", false);
	$("#admin-update-form input:checkbox:eq('2')").attr("checked", false);
	$("#admin-update-form input:checkbox:eq('3')").attr("checked", false);
	var url = "searchAdministraotr?page=1&search=" + id;
	$.get(url, function(data, status) {
		$("#admin-update-form div #admin-id").val(data.adminList[0].id);
		$("#admin-update-form div #admin-name").val(data.adminList[0].user);
		var str = "";
		if(data.adminList[0].typeManager == 1){
            str = "1";
            $("#admin-update-form input:checkbox:eq('0')").attr("checked", true);
		}
		if(data.adminList[0].goodsManager == 1){
			if(str == ""){
            	str += "2";
            }else{
            	str += ",2";
            }
			$("#admin-update-form input:checkbox:eq('1')").attr("checked", true);
		}
		if(data.adminList[0].orderManager == 1){
			if(str == ""){
            	str += "3";
            }else{
            	str += ",3";
            }
			$("#admin-update-form input:checkbox:eq('2')").attr("checked", true);
		}
		if(data.adminList[0].userManager == 1){
			if(str == ""){
            	str += "4";
            }else{
            	str += ",4";
            }
			$("#admin-update-form input:checkbox:eq('3')").attr("checked", true);
		}
	    $("#admin-add-form #admin-per").val(str);
	})
});


/**
 * 修改密码按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#admin-alertPassword-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchAdministraotr?page=1&search=" + id;
	$.get(url, function(data, status){
		$("#admin-update-password-form div #admin-id").val(data.adminList[0].id);
		$("#admin-update-password-form #admin-name").val(data.adminList[0].user);
		$("#admin-update-password-form #admin-password").val("");
		$("#admin-update-password-form #admin-password-confirm").val("");
	})
})

/**
 * 管理员删除按钮点击事件
 * 删除相对应的管理员
 */
$("#table-content").on("click", "#admin-delete-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "deleteAdministrator?id=" + id;
	if(confirm("确定删除吗")){
		$.get(url,function(data,status){
			if(status == 'success'){
				alert("删除成功!");
				$('#contain').load(path + "/AdministratorManagement");
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
})