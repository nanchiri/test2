package com.management.web.controller.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Item;
import com.management.service.ItemService;
import com.management.service.impl.ItemServiceImpl;
import com.management.utils.WebUtils;

/**
 * 单项添加功能
 *
 */
@WebServlet("/B2CSystem/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		Item item = WebUtils.request2Bean(request, Item.class);
		ItemService service = new ItemServiceImpl();
		service.registItem(item);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
