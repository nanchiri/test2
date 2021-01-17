package com.management.service;

import java.util.List;

import com.management.entities.Goods;

public interface GoodsService {
	
	public List<Goods> searchAllGoods();
	
	public List<Goods> searchGoodsByTypeId(Integer id);
	
	public Goods searchGoodsById(Integer id);
	
	public Goods searchGoodsByName(String name);
	
	public void registGoods(Goods goods);
	
	public void alertGoods(Goods goods);

	public void alertGoods2(Goods goods);

	public void deleteGoods(Integer id);
	

}
