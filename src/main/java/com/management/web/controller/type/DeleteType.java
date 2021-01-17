package com.management.web.controller.type;

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
import com.management.service.TypeService;
import com.management.service.impl.GoodsServiceImpl;
import com.management.service.impl.TypeServiceImpl;

/**
 * 删除分类
 */
@WebServlet("/deleteType")
public class DeleteType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if(id == null){
			return;
		}
		TypeService service = new TypeServiceImpl();
		GoodsService goodsService = new GoodsServiceImpl();
		
		String result = "";
		List<Goods> list = goodsService.searchGoodsByTypeId(Integer.parseInt(id));
		if(list.size() > 0){
			boolean canDelete = true;
			for(Goods goods : list){
				if(goods.getIsDelete() == 0){
					canDelete = false;
					break;
				}
			}
			if(canDelete){
				service.deleteType(Integer.parseInt(id));//执行删除功能
				result = "删除成功!";
			}else{
				result = "该分类下还有商品，请先删除该分类下所属商品!";
			}
		}else{
			service.deleteType(Integer.parseInt(id));//执行删除功能
			result = "删除成功!";
		}
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String res = gson.toJson(map);
		out.print(res);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
