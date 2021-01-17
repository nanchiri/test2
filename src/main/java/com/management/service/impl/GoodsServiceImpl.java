package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.GoodsDao;
import com.management.dao.impl.GoodsDaoImpl;
import com.management.entities.Goods;
import com.management.service.GoodsService;
import com.management.utils.CheckDataValidity;

public class GoodsServiceImpl implements GoodsService {

	GoodsDao dao = new GoodsDaoImpl();

	/**
	 * 查询所有商品
	 */
	@Override
	public List<Goods> searchAllGoods() {

		List<Goods> list = null;

		try {
			list = dao.queryAllGoods();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过分类ID查询相对应的商品
	 */
	@Override
	public List<Goods> searchGoodsByTypeId(Integer id) {

		List<Goods> list = null;
		try {
			list = dao.queryGoodsByType(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过商品ID查询商品
	 */
	@Override
	public Goods searchGoodsById(Integer id) {
		Goods goods = null;
		try {
			goods = dao.queryGoodsById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	/**
	 * 通过商品名查询商品
	 */
	@Override
	public Goods searchGoodsByName(String name) {
		Goods goods = null;
		try {
			goods = dao.queryGoodsByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	/**
	 * 新增商品
	 */
	@Override
	public void registGoods(Goods goods) {

		try {
			dao.addGoods(goods);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改商品
	 */
	@Override
	public void alertGoods(Goods goods) {

		try {
			if(CheckDataValidity.checkGoodsValidate(goods)){
				dao.alertGoods(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void alertGoods2(Goods goods) {
		try {
			dao.alertGoods2(goods);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除商品
	 */
	@Override
	public void deleteGoods(Integer id) {
		try {
			dao.deleteGoods(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
