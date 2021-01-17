package com.management.web.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户员登出功能
 *
 */
@WebServlet("/B2CSystem/UserLogOut")
public class UserLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {//防止非法入侵
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		session.removeAttribute("user");//移除session域的管理员名字
		String path = request.getContextPath();
		response.sendRedirect(path+"/Login");//重定向回登录页面
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
