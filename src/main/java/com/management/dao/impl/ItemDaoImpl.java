package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.ItemDao;
import com.management.entities.Item;
import com.management.utils.MySQLConnectionUtils;

public class ItemDaoImpl implements ItemDao {

	/**
	 * 通过单项ID查询
	 */
	@Override
	public Item queryItemById(Integer id) throws SQLException {

		String sql = "SELECT item.`id`,item.order_id,item.user_id,item.type,goods.`id`，goods.`name`,goods.`price`,item.num FROM item,goods WHERE item.goods_id=goods.id AND item.`id`='"
				+ id + "'";

		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		Item item = new Item();
		while (res.next()) {
			item.setId(res.getInt(1));
			item.setOrder_id(res.getInt(2));
			item.setUser_id(res.getInt(3));
			item.setType(res.getInt(4));
			item.setGoods_id(res.getInt(5));
			item.setGoodsName(res.getString(6));
			item.setGoodsPrice(res.getDouble(7));
			item.setNum(res.getInt(8));
		}
		return item;
	}

	/**
	 * 通过订单ID查询订单项
	 */
	@Override
	public List<Item> queryItemByOrder(Integer id) throws SQLException {

		String sql = "SELECT item.`id`,item.order_id,item.user_id,item.type,goods.`id`，goods.`name`,goods.`price` FROM item,goods WHERE item.goods_id=goods.id AND item.order_id='"
				+ id + "'";

		List<Item> list = new LinkedList<Item>();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			Item item = new Item();
			item.setId(res.getInt(1));
			item.setOrder_id(res.getInt(2));
			item.setUser_id(res.getInt(3));
			item.setType(res.getInt(4));
			item.setGoods_id(res.getInt(5));
			item.setGoodsName(res.getString(6));
			item.setGoodsPrice(res.getDouble(7));
			item.setNum(res.getInt(8));
			list.add(item);
		}
		return list;
	}

	/**
	 * 添加单项
	 */
	@Override
	public void addItem(Item item) throws SQLException {

		String sql = "INSERT INTO `item` (`id`, `order_id`, `user_id`, `type`, `goods_id`, `num`) VALUES (?,?,?,?,?,?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, item.getId());
		ps.setInt(2, item.getOrder_id());
		ps.setInt(3, item.getUser_id());
		ps.setInt(4, item.getType());
		ps.setInt(5, item.getGoods_id());
		ps.setInt(6, item.getNum());
		ps.execute();
		
		ps.close();
		con.close();
	}

	/**
	 * 修改单项
	 */
	@Override
	public void alertItem(Item item) throws SQLException {

		String sql = "UPDATE `item` SET `order_id`=?, `user_id`, `type`, `goods_id`, `num`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		Integer id = item.getId();
		ps.setInt(1, item.getOrder_id());
		ps.setInt(2, item.getUser_id());
		ps.setInt(3, item.getType());
		ps.setInt(4, item.getGoods_id());
		ps.setInt(5, item.getNum());
		ps.setInt(6, id);

		ps.execute();
		
		ps.close();
		con.close();

	}

	/**
	 * 删除单项
	 */
	@Override
	public void deleteItem(Integer id) throws SQLException {

		String sql = "DELETE FROM `item` WHERE (`id`='" + id + "')";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);
		
		statement.close();
		con.close();
	}

	/**
	 * 删除指定用户的订单
	 */
	@Override
	public List<Item> queryItemByUser(Integer id) throws SQLException {
		
		String sql = "SELECT item.`id`,item.order_id,item.user_id,item.type,goods.`id`，goods.`name`,goods.`price` FROM item,goods WHERE item.goods_id=goods.id AND item.user_id='"
				+ id + "' AND item.type = 1";

		List<Item> list = new LinkedList<Item>();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			Item item = new Item();
			item.setId(res.getInt(1));
			item.setOrder_id(res.getInt(2));
			item.setUser_id(res.getInt(3));
			item.setType(res.getInt(4));
			item.setGoods_id(res.getInt(5));
			item.setGoodsName(res.getString(6));
			item.setGoodsPrice(res.getDouble(7));
			item.setNum(res.getInt(8));
			list.add(item);
		}
		return list;
		
	}

}
