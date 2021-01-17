package com.management.web.controller.type;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.Type;
import com.management.service.TypeService;
import com.management.service.impl.TypeServiceImpl;
import com.management.utils.PageUtils;

/**
 * 分类搜索功能
 *
 */
@WebServlet("/searchType")
public class SearchType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Integer page = Integer.parseInt(request.getParameter("page"));
		String search = request.getParameter("search");
		if(page == null || search == null){
			return;
		}
		TypeService service = new TypeServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Type Type = null;
		List<Type> typeList = new LinkedList<Type>();
		Integer listCount = null;
		if(search.matches("\\d+")){//判断搜索内容为纯数字，则按分类ID来搜索
			Type = service.searchTypeById(Integer.parseInt(search));
			typeList.add(Type);
		}else{//其他则按照分类名称来搜索
			Type = service.searchTypeByName(new String(search.getBytes("iso8859-1"),"UTF-8"));
			typeList.add(Type);
		}
		//分页功能
		listCount = typeList.size();
		Integer pages = PageUtils.pagesHandler(listCount);
		if(page == pages || pages == 0){
			typeList = typeList.subList((page - 1) * 10, listCount);
		}else{
			typeList = typeList.subList((page - 1) * 10, page * 10);
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		map.put("typeList", typeList);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("allTypeCount", listCount);
		map.put("search", search);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		String result = g.toJson(map);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

}
