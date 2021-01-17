package com.management.web.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.User;
import com.management.service.UserService;
import com.management.service.impl.UserServiceImpl;
import com.management.utils.WebUtils;

/**
 * 用户登录功能
 */
@WebServlet("/B2CSystem/UserLoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user = WebUtils.request2Bean(request, User.class);//获得表单传来的参数
		UserService service = new UserServiceImpl();
		HttpSession session = request.getSession();

		String account = user.getAccount();
		String password = user.getPassword();
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> userMap = new HashMap<String, Object>();
		boolean loginResult = service.login(account, password);//检查账号、密码能否通过登录
		if (loginResult) {//登录通过执行事件
			User userOut = service.searchUserByAccount(account);
			userMap.put("account",userOut.getAccount());
			userMap.put("id",userOut.getId());
			if (request.getParameter("remeberMe") != null && request.getParameter("remeberMe").equals("on")) {
				//检查是否勾选了记住我，需要先检查获取是否为空，不然会报空指针异常
				session.setAttribute("user", account);
				session.setMaxInactiveInterval(7 * 24 * 3600);// Session保存7天
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(7 * 24 * 3600);// cookie的有效期也为7天
				cookie.setPath("/");
				response.addCookie(cookie);//设置Cookie
				map.put("result", "success");
				map.put("user", userMap);
//				response.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+request.getContextPath()+"/Home'</script>");
			} else {
				//没有勾选“记住我”,使用非cookie功能登录
				session.setAttribute("user", account);
				map.put("result", "success");
				map.put("user", userMap);
//				response.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+request.getContextPath()+"/Home'</script>");
			}
		} else {//登录失败
			map.put("result", "登录失败，用户名或密码有误");
//			response.getWriter().write("<script language='JavaScript'>alert('您的用户名或密码有误，请重新输入或者注册');window.location.href='"+request.getContextPath()+"/Login'</script>");
		}
		Gson gson = new Gson();
		String res = gson.toJson(map);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
