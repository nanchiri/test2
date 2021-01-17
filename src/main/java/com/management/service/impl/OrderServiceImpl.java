package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.OrderDao;
import com.management.dao.impl.OrderDaoImpl;
import com.management.entities.Order;
import com.management.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao dao = new OrderDaoImpl();

	/*
	 * 搜索全部订单
	 */
	@Override
	public List<Order> searchAllOrder() {

		List<Order> list = null;
		try {
			list = dao.queryAllOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 通过ID搜索订单
	 */
	@Override
	public Order searchOrderById(Integer id) {

		Order Order = null;
		try {
			Order = dao.queryOrderById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Order;
	}

	/*
	 * 通过用户搜索订单
	 */
	@Override
	public List<Order> searchOrderByUser(Integer id) {

		List<Order> orderList = null;
		try {
			orderList = dao.queryOrderByUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/*
	 * 新增订单
	 */
	@Override
	public void RegistOrder(Order Order) {
		try {
			dao.addOrder(Order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 修改订单
	 */
	@Override
	public void AlertOrder(Order Order) {

		try {
			dao.alertOrder(Order);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
