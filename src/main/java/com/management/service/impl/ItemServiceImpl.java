package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.ItemDao;
import com.management.dao.impl.ItemDaoImpl;
import com.management.entities.Item;
import com.management.service.ItemService;

public class ItemServiceImpl implements ItemService {

	ItemDao dao = new ItemDaoImpl();

	/**
	 * 通过订单ID查询
	 */
	@Override
	public List<Item> searchItemByOrder(Integer id) {

		List<Item> list = null;
		try {
			list = dao.queryItemByOrder(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 通过用户ID购物车项
	 * type=1
	 */
	@Override
	public List<Item> searchItemByUser(Integer id) {

		List<Item> list = null;
		try {
			list = dao.queryItemByUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过单项ID查询
	 */
	@Override
	public Item searchItemById(Integer id) {
		Item Item = null;
		try {
			Item = dao.queryItemById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Item;
	}

	/**
	 * 新增单项
	 */
	@Override
	public void registItem(Item Item) {

		try {
			dao.addItem(Item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 修改单项
	 */
	@Override
	public void alertItem(Item Item) {

		try {
			dao.alertItem(Item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 删除单项
	 */
	@Override
	public void deleteItem(Integer id) {
		try {
			dao.deleteItem(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
