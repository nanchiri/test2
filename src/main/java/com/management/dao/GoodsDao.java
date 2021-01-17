package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Goods;

public interface GoodsDao {
	
	public List<Goods> queryAllGoods() throws SQLException;
	
	public Goods queryGoodsById(Integer id) throws SQLException;
	
	public List<Goods> queryGoodsByType(Integer id) throws SQLException;
	
	public Goods queryGoodsByName(String name) throws SQLException;
	
	public void addGoods(Goods goods) throws SQLException;
	
	public void alertGoods(Goods goods) throws SQLException;

	public void alertGoods2(Goods goods) throws SQLException;

	public void deleteGoods(Integer id) throws SQLException;
	
	public boolean existGoods(String goodsName) throws SQLException;


}
