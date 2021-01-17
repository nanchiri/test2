package com.management.web.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.management.entities.User;
import com.management.service.UserService;
import com.management.service.impl.UserServiceImpl;
import com.management.utils.PageUtils;

/**
 * 获取所有用户
 */
@WebServlet("/UserManagementContent")
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		UserService service = new UserServiceImpl();
		
		// 处理页码
		Integer listCount = service.countAllUser();//获取用户总数
		if(request.getParameter("page") == null){
			return;
		}
		Integer page = Integer.parseUnsignedInt(request.getParameter("page"));//获得传来的当前页
		Integer prePage = PageUtils.prePageHandler(page);//计算出上一页页码
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);//计算出下一页页码
		Integer pages = PageUtils.pagesHandler(listCount);//计算出总共分为多少页
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);//使用列表装载将要表示的页数
		List<User> list = null;
		
		//判断当前页是否等于尾页，进行伪分页
		if(page == pages || pages == 0){
			list = service.getAllUser().subList((page - 1) * 10, listCount);
		}else{
			list = service.getAllUser().subList((page - 1) * 10, page * 10);
		}
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userList", list);
		map.put("allUserCount", listCount);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		
		String content = gson.toJson(map);
		out.print(content);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
