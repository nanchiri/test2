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

import com.google.gson.Gson;
import com.management.entities.User;
import com.management.exception.UserExistException;
import com.management.service.UserService;
import com.management.service.impl.UserServiceImpl;
import com.management.utils.WebUtils;

/**
 * 用户注册
 */
@WebServlet("/B2CSystem/register")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = WebUtils.request2Bean(request, User.class);//获得表单传来的参数
		UserService service = new UserServiceImpl();
		String account = user.getAccount();
		String password = user.getPassword();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if (account != "" && password != "") {
				System.out.println("acc:"+account);
				System.out.println("paw:"+password);
				service.regiest(user);
				map.put("result", "success");
			}else {
				map.put("result", "fail");
				map.put("error", "输入为空");
			}
		} catch (UserExistException e) {
			// TODO: handle exception
			map.put("result", "fail");
			map.put("error", user.getError());
		}
		Gson gson = new Gson();
		String res = gson.toJson(map);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res);
	}

}
