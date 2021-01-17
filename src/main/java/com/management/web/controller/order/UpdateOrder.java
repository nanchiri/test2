package com.management.web.controller.order;

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
import com.management.entities.Order;
import com.management.service.OrderService;
import com.management.service.impl.OrderServiceImpl;
import com.management.utils.WebUtils;

/**
 * 订单更新功能
 *
 */
@WebServlet("/updateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		OrderService service = new OrderServiceImpl();
		Order order = WebUtils.request2Bean(request, Order.class);
		service.AlertOrder(order);
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", "修改成功!");
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String res = gson.toJson(map);
		out.print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
