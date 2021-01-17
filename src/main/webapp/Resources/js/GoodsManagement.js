var vmGoodsContent = new Vue({
	el : '#contain',
	data : {
		goodsList : [],
		allGoodsCount : "",
		prePage : "",
		nextPage : "",
		pageNum : [],
		page : "",
		search : "",
		flag : true
	},
	mounted : function() {// 初始化页面执行方法
		var self = this;
		$.get("GoodsManagementContent?page=1", function(data, status) {
			self.goodsList = data.goodsList;
			self.page = data.page;
			self.prePage = data.prePage;
			self.nextPage = data.nextPage;
			self.pageNum = data.pageNum;
			self.allGoodsCount = data.allGoodsCount;
			self.search = "";
			self.flag = true;
		});
	},
	methods : {
		pageChange : function(page) {
			this.$nextTick(function() {
				$.get("GoodsManagementContent?page=" + page, function(data,
						status) {
					vmGoodsContent.goodsList = data.goodsList;
					vmGoodsContent.page = data.page;
					vmGoodsContent.prePage = data.prePage;
					vmGoodsContent.nextPage = data.nextPage;
					vmGoodsContent.pageNum = data.pageNum;
					vmGoodsContent.allGoodsCount = data.allGoodsCount;
					vmGoodsContent.search = "";
					vmGoodsContent.flag = true;
				});
			});
		},
		searchFunction : function() {
			this.$nextTick(function() {
				$.get("SearchGoods?page=1&search="
						+ $(".goods-search-bar").val(), function(data, status) {
					vmGoodsContent.goodsList = data.goodsList;
					vmGoodsContent.page = data.page;
					vmGoodsContent.prePage = data.prePage;
					vmGoodsContent.nextPage = data.nextPage;
					vmGoodsContent.pageNum = data.pageNum;
					vmGoodsContent.allGoodsCount = data.allGoodsCount;
					vmGoodsContent.search = data.search;
					vmGoodsContent.flag = false;
				})
			})
		},
		searchPage : function(page, search) {
			this.$nextTick(function() {
				$.get("SearchGoods?page=" + page + "&search=" + search,
						function(data, status) {
							vmGoodsContent.goodsList = data.goodsList;
							vmGoodsContent.page = data.page;
							vmGoodsContent.prePage = data.prePage;
							vmGoodsContent.nextPage = data.nextPage;
							vmGoodsContent.pageNum = data.pageNum;
							vmGoodsContent.allGoodsCount = data.allGoodsCount;
							vmGoodsContent.search = data.search;
							vmGoodsContent.flag = false;
						})
			})
		}
	}
});

/*
 * 添加商品功能表单校验
 */
$("#goods-add-form").validator(
		{
			rules : {
				nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "商品名称必须是中文或者英文" ],
			},
			fields : {
				'type_id' : "required",
				'name' : "required;nameValidate;remote(" + $("#path").val()
						+ "/goodsIsExist)",
				'price' : "required;range(0~)",
				'isShow' : "required"
			},
			valid : $("#goods-add-input").click(function() {
				var formData = new FormData($("#goods-add-form")[0]);
				$.ajax({
					url : 'AddGoods',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(returndata) {
						alert(returndata.result);
						if (returndata.result == "添加成功!") {
							$("#goods-add").modal('hide');
							$(".modal-backdrop").remove();
							var path = $("#path").val();
							$('#contain').load(path + "/GoodsManagement");
						}
					},
					error : function() {
						alert("服务器出现未知错误，添加失败!");
					}
				});
			})
		/**
		 * 新增商品表单的提交按钮点击事件 提交表单
		 */
		})

/*
 * 修改商品表单校验
 */
$("#goods-update-form").validator(
		{
			rules : {
				nameValidate : [ /[\u4e00-\u9fa5_a-zA-Z_]+/, "商品名称必须是中文或者英文" ],
			},
			fields : {
				'type_id' : "required",
				'name' : "required;nameValidate;remote(" + $("#path").val()
						+ "/goodsIsExist)",
				'price' : "required;range(0~)",
				'isShow' : "required"
			},

			/**
			 * 表单验证通过后商品更新提交按钮 提交表单
			 */
			valid : $("#goods-update-input").click(function() {
				var formData = new FormData($("#goods-update-form")[0]);
				$.ajax({
					url : 'updateGoods',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(returndata) {
						alert(returndata.result);
						if (returndata.result == "修改成功!") {
							$("#goods-update").modal('hide');
							$(".modal-backdrop").remove();
							var path = $("#path").val();
							$('#contain').load(path + "/GoodsManagement");
						}
					},
					error : function() {
						alert("服务器出现未知错误，添加失败!");
					}
				});
			})
		});

/**
 * 点击添加商品按钮事件 清空表单内容
 */
$("#goods-add-button").click(
		function() {
			$("#goods-add-form div #goods-id").val("");
			$("#goods-add-form div #goods-name").val("");
			$("#goods-add-form div #goods-type").val("");
			$("#goods-add-form div #goods-type").html("");
			$.get("getType", function(data, status) {
				var type = data;
				var res = "<option value=''>---请选择---</option>";
				for (var i = 0; i < type.length; i++) {
					res += "<option value='" + type[i].id + "'>" + type[i].name
							+ "</option>";
				}
				$("#goods-add-form div #goods-type").append(res);
			});

		});

/**
 * 修改按钮点击事件 回显表单
 */
$("#table-content")
		.on(
				"click",
				"#goods-update-button",
				function() {
					var id = $(this).val();
					var path = $("#path").val();
					var url = "SearchGoods?page=1&search=" + id;
					$.get("getType", function(data, status) {
						var type = data;
						$("#goods-update-form div #goods-type").html("");
						var res = "<option value=''>---请选择---</option>";
						for (var i = 0; i < type.length; i++) {
							res += "<option value='" + type[i].id + "'>"
									+ type[i].name + "</option>";
						}
						$("#goods-update-form div #goods-type").append(res);
					});
					$
							.get(
									url,
									function(data, status) {
										$("#goods-update-form div #goods-id")
												.val(data.goodsList[0].id);
										$("#goods-update-form div #goods-name")
												.val(data.goodsList[0].name);
										$(
												"#goods-update-form div #goods-type option")
												.each(
														function() {
															if ($(this).text() == data.goodsList[0].typeName) {
																$(this)
																		.attr(
																				"selected",
																				"selected");
															}
														});
										$("#goods-update-form div #goods-price")
												.val(data.goodsList[0].price);
										$(
												"#goods-update-form div #goods-show option")
												.each(
														function() {
															if ($(this).attr(
																	"value") == data.goodsList[0].isShow) {
																$(this)
																		.attr(
																				"selected",
																				"selected");
															}
														});
									});
				});

$('#goods-add,#goods-update').on('hide.bs.modal', function() {
	setTimeout(clear, 200)
})

function clear() {
	var res = "<option value=''>---请选择---</option><option value='0'>不显示</option><option value='1'>Section1</option><option value='2'>Section2</option>"
	var res2 = "<label for='goods-img' style='width: 100px;'>商品图片1:</label><input type='file' name='img1' />";
	$("#goods-add-form #goods-img").html(res2);
	$("#goods-update-form #goods-img").html(res2);
	$("#goods-update-form div #goods-show").html(res);
	img_id = 1;
}

/**
 * 商品删除按钮点击事件
 */
$("#table-content").on("click", "#goods-delete-button", function() {
	var id = $(this).val();
	var path = $("#path").val();
	var url = "deleteGoods?id=" + id;
	if (confirm("确定删除吗")) {
		$.get(url, function(data, status) {
			if (status == 'success') {
				alert("删除成功!");
				$('#contain').load(path + "/GoodsManagement");
			} else {
				alert("删除失败，可能您的网络有问题!");
			}
		});
	}
})

var img_id = 1;

$("#goods-add-img,#goods-update-img").click(
		function() {
			img_id += 1 ;
			console.log(img_id)
			var res = "<label for='goods-img' style='width: 100px;'>商品图片"+img_id+":</label><input type='file' name='img"+img_id+"' />";
			$("#goods-add-form #goods-img").append(res);
			$("#goods-update-form #goods-img").append(res);
		});