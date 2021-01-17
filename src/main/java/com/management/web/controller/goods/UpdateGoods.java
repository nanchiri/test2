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
import com.management.utils.CheckDataValidity;
import com.management.utils.ImgUtils;

/**
 * 更新商品功能
 *
 */
@WebServlet("/updateGoods")
public class UpdateGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String result = "";
		try {
			Goods goods = ImgUtils.saveFile(request, Goods.class);
			GoodsService service = new GoodsServiceImpl();
			Goods oldGoods = service.searchGoodsByName(goods.getName());

			if (oldGoods.getId() != null && oldGoods.getId().intValue() != goods.getId().intValue()) {// 判断分类名是否存在
				result = "该商品名已被占用!";
			} else {
				if (!CheckDataValidity.checkGoodsValidate(goods)) {
					result = "商品名必须为中文或者是英文!";
				} else {
					Integer amount = (Integer) request.getAttribute("amount");
					service.registGoods(goods);
					service.alertGoods(goods);
					result = "修改成功!";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "文件大小不得大于3M！";
		}
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String res = gson.toJson(map);
		out.print(res);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
