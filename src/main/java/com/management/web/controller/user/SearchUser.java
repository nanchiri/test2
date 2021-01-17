package com.management.web.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.management.entities.User;
import com.management.service.UserService;
import com.management.service.impl.UserServiceImpl;
import com.management.utils.PageUtils;

/**
 * 用户搜索功能
 */
@WebServlet("/B2CSystem/searchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		Integer page = Integer.parseUnsignedInt(request.getParameter("page"));
		String search = request.getParameter("search");
		if(search== null || page == null){
			return;
		}
		UserService service = new UserServiceImpl();
		User user= null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userList = new LinkedList<User>();
		Integer listCount = null;
		if(search.matches("\\d+")){
			if(search.length() < 5){//如果搜索内容全为数字，则判断为按管理员ID进行搜索
				user = service.searchUserById(Integer.parseInt(search));
				userList.add(user);
				listCount = userList.size();
			}else{//按账号搜索
				user = service.searchUserByAccount(search);
				userList.add(user);
				listCount = userList.size();
			}
			Integer pages = PageUtils.pagesHandler(listCount);//分页功能
			if(page == pages || pages == 0){
				userList = userList.subList((page - 1) * 10, listCount);
			}else{
				userList = userList.subList((page - 1) * 10, page * 10);
			}
			
			Integer prePage = PageUtils.prePageHandler(page);
			Integer nextPage = PageUtils.nextPageHandler(page, listCount);
			List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
			
			map.put("userList", userList);
			map.put("prePage", prePage);
			map.put("nextPage", nextPage);
			map.put("pageNum", pageNum);
			map.put("page", page);
			map.put("allUserCount", listCount);
			map.put("search", search);
			
			response.setHeader("Content-Type", "application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			Gson g = new Gson();
			String result = g.toJson(map);
			out.print(result);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
