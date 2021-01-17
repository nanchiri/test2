var vmTypeContent = new Vue({
	el : '#contain',
	data : {
		typeList : [],
		allTypeCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search:"",
		flag:true
	},
	mounted : function() {//初始话执行方法
		var self = this;
		$.get("TypeManagementContent?page=1", function(data, status) {
			self.typeList = data.typeList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allTypeCount = data.allTypeCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {
			this.$nextTick(function() {
				$.get("TypeManagementContent?page=" + page, function(data, status) {
					vmTypeContent.typeList = data.typeList;
					vmTypeContent.page = data.page;
					vmTypeContent.prePage = data.prePage;
					vmTypeContent.nextPage = data.nextPage;
					vmTypeContent.pageNum = data.pageNum;
					vmTypeContent.allTypeCount = data.allTypeCount;
					vmTypeContent.search = "";
					vmTypeContent.flag = true;
				});
			});
		},
		searchFunction:function(){
			this.$nextTick(function(){
				$.get("searchType?page=1&search="+$(".type-search-bar").val(),function(data,status){
					vmTypeContent.typeList = data.typeList;
					vmTypeContent.page = data.page;
					vmTypeContent.prePage = data.prePage;
					vmTypeContent.nextPage = data.nextPage;
					vmTypeContent.pageNum = data.pageNum;
					vmTypeContent.allTypeCount = data.allTypeCount;
					vmTypeContent.search = data.search;
					vmTypeContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){
			this.$nextTick(function(){
				$.get("searchType?page="+page+"&search="+search,function(data,status){
					vmTypeContent.typeList = data.typeList;
					vmTypeContent.page = data.page;
					vmTypeContent.prePage = data.prePage;
					vmTypeContent.nextPage = data.nextPage;
					vmTypeContent.pageNum = data.pageNum;
					vmTypeContent.allTypeCount = data.allTypeCount;
					vmTypeContent.search = data.search;
					vmTypeContent.flag = false;
				})
			})
		}
	}
});

/*
 * 添加分类功能表单校验
 */
$("#type-add-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "分类名称必须是中文或者英文" ],
	},
	fields : {
		'name' : "required;nameValidate;remote(" + $("#path").val() + "/typeIsExist)"
	},
	/**
	 * 新增分类表单的提交按钮点击事件
	 * 提交表单
	 */
	valid : $("#type-add-input").click(function() {
		$.post("addType", $("#type-add-form").serialize(), function(data, status) {
			if (status == "success") {
				alert(data.result);
				if(data.result == "添加成功!"){
					$("#type-add").modal('hide');
					$(".modal-backdrop").remove();
					var path = $("#path").val();
					$('#contain').load(path + "/TypeManagement");
				}
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/*
 * 修改分类表单校验
 */
$("#type-update-form").validator({
	rules : {
		nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "分类名称必须是中文或者英文" ],
	},
	fields : {
		'name' : "required;nameValidate;remote(" + $("#path").val() + "/typeIsExist)"
	},

	/**
	 * 表单验证通过后分类修改更新提交按钮
	 * 提交表单
	 */
	valid : $("#type-update-input").click(function() {
		$.post("updateType", $("#type-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert(data.result);
				if(data.result == "修改成功!"){
					$("#type-update").modal('hide');
					$(".modal-backdrop").remove();
					var path = $("#path").val();
					$('#contain').load(path + "/TypeManagement");
				}
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});



/**
 * 添加按钮事件
 * 清空表单内容
 */
$("#type-add-button").click(function(){
	$("#type-add-form div #type-id").val("");
	$("#type-add-form div #type-name").val("");
});

/**
 * 修改按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#type-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchType?page=1&search=" + id;
	$.get(url, function(data, status) {
		$("#type-update-form div #type-id").val(data.typeList[0].id);
		$("#type-update-form div #type-name").val(data.typeList[0].name);
	});
});


/**
 * 删除按钮点击事件
 * 删除相对应的分类
 */
$("#table-content").on("click", "#type-delete-button",function(){
	var id = $(this).val();
	var path = $("#path").val();
	var url = "deleteType?id=" + id;
	if(confirm("确定删除吗")){
		$.get(url,function(data,status){
			if(status == 'success'){
				alert(data.result);
				if(data.result == "删除成功!"){
					$('#contain').load(path + "/TypeManagement");
				}
			}else{
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
})