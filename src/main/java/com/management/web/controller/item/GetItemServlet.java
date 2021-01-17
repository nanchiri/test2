package com.management.web.controller.item;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.Item;
import com.management.service.ItemService;
import com.management.service.impl.ItemServiceImpl;

/**
 * 根据用户/订单获得相对应的单项
 *
 */
@WebServlet("/B2CSystem/getItem")
public class GetItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		ItemService service = new ItemServiceImpl();
		List<Item> list = null;
		if(request.getParameter("orderId") == null){
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			list = service.searchItemByUser(userId);
		}else{
			Integer orderId = Integer.parseInt(request.getParameter("orderId"));
			list = service.searchItemByOrder(orderId);
		}
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String item = gson.toJson(list);
		out.print(item);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
