package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Item;

public interface ItemDao {
	
	public Item queryItemById(Integer id) throws SQLException;
	
	public List<Item> queryItemByOrder(Integer id) throws SQLException;
	
	public List<Item> queryItemByUser(Integer id) throws SQLException;
	
	public void addItem(Item item) throws SQLException;
	
	public void alertItem(Item item) throws SQLException;
	
	public void deleteItem(Integer id) throws SQLException;

}
