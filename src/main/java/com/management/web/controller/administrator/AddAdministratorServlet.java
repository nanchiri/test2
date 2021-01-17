package com.management.web.controller.administrator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.Administrator;
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;
import com.management.utils.WebUtils;


/**
 * 管理员添加功能
 */
@WebServlet("/addAdministrator")
public class AddAdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		AdministratorService service = new AdministratorServiceImpl();
		Administrator admin = WebUtils.request2Bean(request, Administrator.class);//获取request域里面的参数，并且把相对应的参数放入admin中
		Administrator oldAdmin = service.searchAdministratorByName(admin.getUser());
		
		String result = "";
		if(oldAdmin.getId() != null){//判断账号是否存在
			result = "该账号已被占用!";
		}else{
			if (admin.getUser() == null || admin.getUser().trim().equals("")) {
				result = "用户名不能为空";
			}
			if (admin.getPassword() == null || admin.getPassword().trim().equals("")) {
				result = "密码不能为空";
			} else if (!admin.getPassword().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$")) {
				result = "密码需要为字母数字混合,限制为6-14位";
			}else{
				admin.setTypeManager(0);
				admin.setGoodsManager(0);
				admin.setOrderManager(0);
				admin.setUserManager(0);
				String[] split = admin.getData().split(",");//以逗号分割
		        for (String per : split) {
		            if(per.equals("1")){
		            	admin.setTypeManager(1);
		            }else if(per.equals("2")){
		            	admin.setGoodsManager(1);
		            }else if(per.equals("3")){
		            	admin.setOrderManager(1);
		            }else if(per.equals("4")){
		            	admin.setUserManager(1);
		            }
		        }
				service.regiest(admin);
				result = "添加成功!";
			}
		}
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String res = gson.toJson(map);
		out.print(res);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
