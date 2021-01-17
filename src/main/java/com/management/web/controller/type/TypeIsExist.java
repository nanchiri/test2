package com.management.web.controller.type;

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
import com.management.entities.Type;
import com.management.service.TypeService;
import com.management.service.impl.TypeServiceImpl;

/**
 * 检测分类名是否重复(分类名不可重复)
 */
@WebServlet("/typeIsExist")
public class TypeIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String typeName = request.getParameter("name");
		if(typeName == null){
			return;
		}
		TypeService service = new TypeServiceImpl();
		Map<String, String> map = new HashMap<String, String>();
		Type type = service.searchTypeByName(typeName);
		if(type.getId() != null){//判断分类名是否存在
			map.put("error", "该分类名已被占用!");
		}else{
			map.put("ok","名字能够使用!");
		}
		
		Gson gson = new Gson();
		String res = gson.toJson(map);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
