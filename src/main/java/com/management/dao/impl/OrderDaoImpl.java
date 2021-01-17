package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.OrderDao;
import com.management.entities.Order;
import com.management.utils.MySQLConnectionUtils;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 查询所有订单
	 */
	@Override
	public List<Order> queryAllOrder() throws SQLException {
		String sql = "SELECT t_order.id,t_order.amount,t_order.user_id,user.account FROM t_order,user WHERE t_order.user_id=user.id";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		List<Order> list = new LinkedList<Order>();

		while (res.next()) {
			Order order = new Order();
			order.setId(res.getInt(1));
			order.setAmount(res.getDouble(2));
			order.setUser_id(res.getInt(3));
			order.setUserName(res.getString(4));
			list.add(order);
		}

		return list;
	}

	/**
	 * 通过订单ID查询
	 */
	@Override
	public Order queryOrderById(Integer id) throws SQLException {

		String sql = "SELECT t_order.id,t_order.amount,t_order.user_id,user.account FROM t_order,user WHERE t_order.user_id=user.id AND t_order.id='" + id + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		Order order = new Order();

		while (res.next()) {
			order.setId(res.getInt(1));
			order.setAmount(res.getDouble(2));
			order.setUser_id(res.getInt(3));
			order.setUserName(res.getString(4));
		}

		return order;
	}

	/**
	 * 创建订单
	 */
	@Override
	public void addOrder(Order order) throws SQLException {

		String sql = "INSERT INTO `t_order` (`amount`, `user_id`,`goods_id`) VALUES (?,?,?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setDouble(1, order.getAmount());
		ps.setInt(2, order.getUser_id());
		ps.setInt(3, order.getGoods_id());

		ps.execute();
		
		ps.close();
		con.close();

	}

	/**
	 * 修改订单
	 */
	@Override
	public void alertOrder(Order order) throws SQLException {

		String sql = "UPDATE `t_order` SET `amount`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setDouble(1, order.getAmount());
		ps.setInt(2, order.getId());

		ps.execute();
		
		ps.close();
		con.close();

	}
	
	/**
	 * 通过用户ID查询
	 */

	@Override
	public List<Order> queryOrderByUser(Integer id) throws SQLException {
		
		String sql = "SELECT t_order.id,t_order.amount,t_order.user_id,user.account FROM t_order,user WHERE t_order.user_id=user.id AND t_order.user_id='" + id + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		List<Order> list = new LinkedList<Order>();

		while (res.next()) {
			Order order = new Order();
			order.setId(res.getInt(1));
			order.setAmount(res.getDouble(2));
			order.setUser_id(res.getInt(3));
			order.setUserName(res.getString(4));
			list.add(order);
		}

		return list;
		
	}

}
