package com.management.web.controller.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Goods;
import com.management.entities.Order;
import com.management.service.GoodsService;
import com.management.service.OrderService;
import com.management.service.impl.GoodsServiceImpl;
import com.management.service.impl.OrderServiceImpl;
import com.management.utils.WebUtils;

/**
 * 添加功能
 */
@WebServlet("/B2CSystem/addOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		HttpSession session = request.getSession();
//		if (session.getAttribute("admin") == null) {
//			response.sendRedirect(request.getContextPath() + "/Login");
//			return;
//		}

		OrderService service = new OrderServiceImpl();
		Order order = WebUtils.request2Bean(request, Order.class);

		GoodsService goodsService = new GoodsServiceImpl();
		Goods goods = goodsService.searchGoodsById(order.getGoods_id());
		int amount = goods.getAmount();
		System.out.println("商品信息" + goods + "---------  数量：" + amount);
		if (amount > 0) {
			service.RegistOrder(order);
			goodsService.alertGoods2(goods);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
