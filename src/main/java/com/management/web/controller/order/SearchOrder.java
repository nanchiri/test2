package com.management.web.controller.order;

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
import com.management.entities.Order;
import com.management.service.OrderService;
import com.management.service.impl.OrderServiceImpl;
import com.management.utils.PageUtils;

/**
 * 订单搜索功能
 *
 */
@WebServlet("/searchOrder")
public class SearchOrder extends HttpServlet {
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
		OrderService service = new OrderServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Order order = null;
		List<Order> orderList = new LinkedList<Order>();
		Integer listCount = null;
		if(search.matches("\\d+")){//判断搜索内容为纯数字，则按订单ID来搜索
			order = service.searchOrderById(Integer.parseInt(search));
			orderList.add(order);
		}else{//其他则按照用户ID来搜索
			orderList = service.searchOrderByUser(Integer.parseInt(search));
		}
		//分页功能
		listCount = orderList.size();
		Integer pages = PageUtils.pagesHandler(listCount);
		if(page == pages || pages == 0){
			orderList = orderList.subList((page - 1) * 10, listCount);
		}else{
			orderList = orderList.subList((page - 1) * 10, page * 10);
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		map.put("orderList", orderList);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("allOrderCount", listCount);
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
		doGet(request, response);
	}

}
