package com.management.service;

import java.util.List;

import com.management.entities.Order;

public interface OrderService {

	public List<Order> searchAllOrder();
	
	public Order searchOrderById(Integer id);
	
	public List<Order> searchOrderByUser(Integer id);
	
	public void RegistOrder(Order order);
	
	public void AlertOrder(Order order);
	
}
