/*
 * 点击侧边的菜单事件
 * 让main根据地址刷新相对应的内容
 */
$(function() {
	var first = "";
	var firstC = "";
	if($("#permission").val()!=1){
		$("#adminManagement").remove();
		if($("#typeManager").val()!=1){
			$("#typeManagement").remove();
		}else{
			first = "/TypeManagement";
			firstC = "#typeManagement";
		}
		if($("#goodsManager").val()!=1){
			$("#goodsManagement").remove();
		}else{
			if(first != ""){
				first = "/GoodsManagement";
				firstC = "#goodsManager";
			}
		}
		if($("#orderManager").val()!=1){
			$("#orderManagement").remove();
		}else{
			if(first != ""){
				first = "/OrderManagement";
				firstC = "#orderManager";
			}
		}
		if($("#userManager").val()!=1){
			$("#userManagement").remove();
		}else{
			if(first != ""){
				first = "/UserManagement";
				firstC = "#userManager";
			}
		}
	}else{
		first = "/AdministratorManagement";
		firstC = "#adminManagement";
	}
	$(firstC).addClass("active");
	var path = $("#path").val();
	$('#contain').load(path + first);
	$(".nav li a").click(function() {
		var type = $(this).attr("value");
		var address = $(this).attr("href");
		$("a").removeClass("active")
		$(this).addClass("active");
		$('#contain').html("");
		if(type != "logout"){
			$('#contain').load(address);
		}
		switch (type) {
		case 'StudentManagement':
			requestStudentContent("StudentManagementContent?page=1");
			break;
		case 'logout':
			if(confirm("确定退出吗")){
				window.location.replace(address);
			}
		}
		return false;
	});

});