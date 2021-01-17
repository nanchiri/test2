package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Order;

public interface OrderDao {
	
	public List<Order> queryAllOrder() throws SQLException;
	
	public Order queryOrderById(Integer id) throws SQLException;
	
	public List<Order> queryOrderByUser(Integer id) throws SQLException;
	
	public void addOrder(Order order) throws SQLException;
	
	public void alertOrder(Order order) throws SQLException;
	
}
