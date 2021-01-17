package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.GoodsDao;
import com.management.entities.Goods;
import com.management.utils.List2String;
import com.management.utils.MySQLConnectionUtils;

public class GoodsDaoImpl implements GoodsDao {

	/**
	 * 查询所有商品
	 */
	@Override
	public List<Goods> queryAllGoods() throws SQLException {

		String sql = "SELECT goods.id,goods.`name`,type.`name`,goods.img,goods.price,goods.is_show,goods.amount FROM goods,type WHERE goods.type_id=type.id AND goods.is_deleted = 0";
		List<Goods> list = new LinkedList<Goods>();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			Goods goods = new Goods();
			goods.setId(res.getInt(1));
			goods.setName(res.getString(2));
			goods.setTypeName(res.getString(3));
			goods.setImg(List2String.string2List(res.getString(4)));
			goods.setPrice(res.getDouble(5));
			goods.setIsShow(res.getInt(6));
			goods.setAmount(res.getInt(7));
			list.add(goods);
		}
		return list;
	}

	/**
	 * 通过ID查询
	 */
	@Override
	public Goods queryGoodsById(Integer id) throws SQLException {

		String sql = "SELECT goods.id,goods.`name`,type.`name`,goods.img,goods.price,goods.is_deleted,goods.is_show,goods.amount FROM goods,type WHERE goods.type_id=type.id AND goods.is_deleted = 0 AND goods.`id`='"
				+ id + "'";

		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		Goods goods = new Goods();
		while (res.next()) {
			goods.setId(res.getInt(1));
			goods.setName(res.getString(2));
			goods.setTypeName(res.getString(3));
			goods.setImg(List2String.string2List(res.getString(4)));
			goods.setPrice(res.getDouble(5));
			goods.setIsDelete(res.getInt(6));
			goods.setIsShow(res.getInt(7));
			goods.setAmount(res.getInt(8));
		}
		return goods;
	}

	/**
	 * 通过分类ID查询相关的商品
	 */
	@Override
	public List<Goods> queryGoodsByType(Integer id) throws SQLException {

		String sql = "SELECT goods.id,goods.`name`,type.`name`,goods.img,goods.price,goods.is_deleted,goods.is_show FROM goods,type WHERE goods.type_id=type.id AND goods.is_deleted = 0 AND goods.type_id='"
				+ id + "'";

		List<Goods> list = new LinkedList<Goods>();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			Goods goods = new Goods();
			goods.setId(res.getInt(1));
			goods.setName(res.getString(2));
			goods.setTypeName(res.getString(3));
			goods.setImg(List2String.string2List(res.getString(4)));
			goods.setPrice(res.getDouble(5));
			goods.setIsDelete(res.getInt(6));
			goods.setIsShow(res.getInt(7));
			list.add(goods);
		}
		return list;
	}

	/**
	 * 通过商品名称查询
	 */
	@Override
	public Goods queryGoodsByName(String name) throws SQLException {

		String sql = "SELECT goods.id,goods.`name`,type.`name`,goods.img,goods.price,goods.is_show FROM goods,type WHERE goods.type_id=type.id AND goods.is_deleted = 0 AND goods.`name`='"
				+ name + "'";

		Goods goods = new Goods();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			goods.setId(res.getInt(1));
			goods.setName(res.getString(2));
			goods.setTypeName(res.getString(3));
			goods.setImg(List2String.string2List(res.getString(4)));
			goods.setPrice(res.getDouble(5));
		}
		return goods;
	}

	/**
	 * 添加商品
	 */
	@Override
	public void addGoods(Goods goods) throws SQLException {

		String sql = "INSERT INTO `goods` (`name`, `type_id`, `img`, `price`, `is_deleted`,`is_show`,`amount`) VALUES (?,?,?,?,?,?,?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, goods.getName());
		ps.setInt(2, goods.getType_id());
		System.out.println("即将输出");
		for (String str : goods.getImg()) {
			System.out.println("这里是输出："+str);
		}
		ps.setString(3, List2String.list2String(goods.getImg()));
		ps.setDouble(4, goods.getPrice());
		ps.setInt(5, 0);
		ps.setInt(6, goods.getIsShow());
		ps.setInt(7,goods.getAmount());
		ps.execute();
		
		ps.close();
		con.close();
	}

	/**
	 * 修改商品
	 */
	@Override
	public void alertGoods(Goods goods) throws SQLException {

		String sql = "UPDATE `goods` SET `name`=?, `type_id`=?, `img`=?, `price`=?, `is_show`=?, `amount`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);


		ps.setString(1, goods.getName());
		ps.setInt(2, goods.getType_id());
		ps.setString(3, List2String.list2String(goods.getImg()));
		ps.setDouble(4, goods.getPrice());
		ps.setInt(5, goods.getIsShow());
		ps.setInt(6,goods.getAmount());
		ps.setInt(7, goods.getId());
		ps.execute();
		
		ps.close();
		con.close();

	}

	/**
	 * 商品数量减1
	 */
	@Override
	public void alertGoods2(Goods goods) throws SQLException {

		String sql = "UPDATE `goods` SET `amount`= `amount` -1 WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, goods.getId());
		ps.execute();

		ps.close();
		con.close();

	}

	/**
	 * 删除商品
	 */
	@Override
	public void deleteGoods(Integer id) throws SQLException {

		String sql = "UPDATE `goods` SET `is_deleted`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, 1);
		ps.setInt(2, id);

		ps.execute();
		
		ps.close();
		con.close();
		
	}

	/**
	 * 查询商品名字是否存在
	 */
	@Override
	public boolean existGoods(String goodsName) throws SQLException {

		String sql = "SELECT * FROM goods WHERE name='" + goodsName + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		return res.next();
	}

}
