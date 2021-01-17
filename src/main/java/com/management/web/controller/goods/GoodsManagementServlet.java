package com.management.web.controller.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
 * 展示全部商品
 *
 */
@WebServlet("/GoodsManagementContent")
public class GoodsManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		GoodsService service = new GoodsServiceImpl();
		List<Goods> goodsList = service.searchAllGoods();
		Integer listCount = goodsList.size();
		if(request.getParameter("page") == null){
			return;
		}
		//分页功能
		Integer page = Integer.parseInt(request.getParameter("page")); 
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		Integer pages = PageUtils.pagesHandler(listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		if(page == pages || pages == 0){
			goodsList = goodsList.subList((page - 1) * 10, listCount);
		}else{
			goodsList = goodsList.subList((page - 1) * 10, page * 10);
		}
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsList", goodsList);
		map.put("allGoodsCount", listCount);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		
		String res = gson.toJson(map);
		out.print(res);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
