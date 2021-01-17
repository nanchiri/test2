package com.management.web.controller.type;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.management.entities.Goods;
import com.management.entities.Type;
import com.management.service.GoodsService;
import com.management.service.TypeService;
import com.management.service.impl.GoodsServiceImpl;
import com.management.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class GetTypeAndGoodsServlet
 */

@WebServlet("/B2CSystem/category")
public class GetTypeAndGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTypeAndGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		TypeService typeService = new TypeServiceImpl();
		GoodsService goodService = new GoodsServiceImpl();
		List<Type> typeList = typeService.searchAllType();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Type type : typeList) {
			Map<String, Object> outMap = new HashMap<String, Object>();
			List<Goods> goodsList = goodService.searchGoodsByTypeId(type.getId());
			outMap.put("banner", type.getName());
			outMap.put("list",goodsList);
			list.add(outMap);
		}
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String goods = gson.toJson(list);
		out.print(goods);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
