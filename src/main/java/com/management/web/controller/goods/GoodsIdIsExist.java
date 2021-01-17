package com.management.web.controller.goods;

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
import com.management.entities.Goods;
import com.management.service.GoodsService;
import com.management.service.impl.GoodsServiceImpl;

/**
 * 判断商品ID是否存在
 *
 */
@WebServlet("/goodsIsExist")
public class GoodsIdIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		
		String goodsName = request.getParameter("name");
		if(goodsName == null){
			return;
		}
		GoodsService service = new GoodsServiceImpl();
		Goods goods = null;
		goods = service.searchGoodsByName(goodsName);
		Map<String, String> map = new HashMap<String, String>();
		
		if(goods.getId() != null){//判断分类名是否存在
			map.put("error", "该商品名已被占用!");
		}else{
			map.put("ok","商品名能够使用!");
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
