package com.management.web.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.service.UserService;
import com.management.service.impl.UserServiceImpl;
import com.management.utils.PasswordEncryptionUtils;

/**
 * 用户修改密码
 *
 */
@WebServlet("/B2CSystem/updateUserPassword")
public class UpdateUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Integer id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		UserService service  = new UserServiceImpl();
		System.out.println("update");
		if(id == null || password == null){
			return;
		}
		password = PasswordEncryptionUtils.plainText2MD5Encrypt(password);//加密密码
		service.updateUserPassword(id, password);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
