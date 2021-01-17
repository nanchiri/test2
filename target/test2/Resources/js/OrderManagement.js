var vmOrderContent = new Vue({
	el : '#contain',
	data : {
		orderList : [],
		allOrderCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search:"",
		flag:true
	},
	mounted : function() {//初始话执行方法
		var self = this;
		$.get("OrderManagementContent?page=1", function(data, status) {
			self.orderList = data.orderList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allOrderCount = data.allOrderCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {
			this.$nextTick(function() {
				$.get("OrderManagementContent?page=" + page, function(data, status) {
					vmOrderContent.orderList = data.orderList;
					vmOrderContent.page = data.page;
					vmOrderContent.prePage = data.prePage;
					vmOrderContent.nextPage = data.nextPage;
					vmOrderContent.pageNum = data.pageNum;
					vmOrderContent.allOrderCount = data.allOrderCount;
					vmOrderContent.search = "";
					vmOrderContent.flag = true;
				});
			});
		},
		searchFunction:function(){
			this.$nextTick(function(){
				$.get("searchOrder?page=1&search="+$(".order-search-bar").val(),function(data,status){
					vmOrderContent.orderList = data.orderList;
					vmOrderContent.page = data.page;
					vmOrderContent.prePage = data.prePage;
					vmOrderContent.nextPage = data.nextPage;
					vmOrderContent.pageNum = data.pageNum;
					vmOrderContent.allOrderCount = data.allOrderCount;
					vmOrderContent.search = data.search;
					vmOrderContent.flag = false;
				})
			})
		},
		searchPage:function(page,search){
			this.$nextTick(function(){
				$.get("searchOrder?page="+page+"&search="+search,function(data,status){
					vmOrderContent.orderList = data.orderList;
					vmOrderContent.page = data.page;
					vmOrderContent.prePage = data.prePage;
					vmOrderContent.nextPage = data.nextPage;
					vmOrderContent.pageNum = data.pageNum;
					vmOrderContent.allOrderCount = data.allOrderCount;
					vmOrderContent.search = data.search;
					vmOrderContent.flag = false;
				})
			})
		}
	}
});

/*
 * 修改分类表单校验
 */
$("#order-update-form").validator({
	fields : {
		'amount' : "required;range(0~)"
	},

	/**
	 * 表单验证通过后分类修改更新提交按钮
	 * 提交表单
	 */
	valid : $("#order-update-input").click(function() {
		$.post("updateOrder", $("#order-update-form").serialize(), function(data, status) {
			if (status == "success") {
				alert(data.result);
				if(data.result == "修改成功!"){
					$("#order-update").modal('hide');
					$(".modal-backdrop").remove();
					var path = $("#path").val();
					$('#contain').load(path + "/OrderManagement");
				}
			} else {
				alert("服务器出现未知错误，添加失败!");
			}
		})
	})
});

/**
 * 修改按钮点击事件
 * 回显表单
 */
$("#table-content").on("click", "#order-update-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "searchOrder?page=1&search=" + id;
	$.get(url, function(data, status) {
		$("#order-update-form div #order-id").val(data.orderList[0].id);
		$("#order-update-form div #order-amount").val(data.orderList[0].amount);
	});
});
