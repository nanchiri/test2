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
import com.management.utils.CheckDataValidity;
import com.management.utils.WebUtils;

/**
 * 添加功能
 */
@WebServlet("/addType")
public class AddType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		TypeService service = new TypeServiceImpl();
		Type type = WebUtils.request2Bean(request, Type.class);
		Type oldType = service.searchTypeByName(type.getName());
		String result = "";
		
		if(oldType.getId() != null){//判断分类名是否存在
			result = "该分类名已被占用!";
		}else{
			if(!CheckDataValidity.checkTypeValidate(type)){
				result = "分类名必须为中文或者是英文!";
			}else{
				service.RegistType(type);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
