package com.management.web.controller.goods;

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
import com.management.entities.Goods;
import com.management.service.GoodsService;
import com.management.service.impl.GoodsServiceImpl;
import com.management.utils.PageUtils;

/**
 * 商品搜索功能
 *
 */
@WebServlet("/SearchGoods")
public class SearchGoods extends HttpServlet {
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
		if(search== null || page == null){
			return;
		}
		
		GoodsService service = new GoodsServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Goods goods = null;
		List<Goods> goodsList = new LinkedList<Goods>();
		Integer listCount = null;
		if(search.matches("\\d+")){
			goods = service.searchGoodsById(Integer.parseInt(search));
			goodsList.add(goods);
			listCount = goodsList.size();
		}else{
			goods = service.searchGoodsByName(new String(search.getBytes("iso8859-1"),"UTF-8"));
			goodsList.add(goods);
			listCount = goodsList.size();
		}
		Integer pages = PageUtils.pagesHandler(listCount);
		if(page == pages || pages == 0){
			goodsList = goodsList.subList((page - 1) * 10, listCount);
		}else{
			goodsList = goodsList.subList((page - 1) * 10, page * 10);
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		map.put("goodsList", goodsList);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("allGoodsCount", listCount);
		map.put("search", search);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		String result = g.toJson(map);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
