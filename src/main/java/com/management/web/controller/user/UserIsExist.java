package com.management.web.controller.user;

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
import com.management.service.UserService;
import com.management.service.impl.UserServiceImpl;

/**
 * 检测账号是否存在（用户账号不可重复）
 *
 */
@WebServlet("/B2CSystem/accountIsExist")
public class UserIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {//判断是否有管理员登录，如没有则表示为非法登录，则调回登录页面
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		if(account == null){
			return;
		}
		UserService service = new UserServiceImpl();
		boolean flag = service.AccountIsExist(account);
		Map<String, String> map = new HashMap<String, String>();
		if(flag){
			map.put("error", "账号已被占用!");
		}else{
			map.put("ok","账号能够使用!");
		}
		Gson gson = new Gson();
		String res = gson.toJson(map);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res);//把结果通过JSON形式来输出
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
