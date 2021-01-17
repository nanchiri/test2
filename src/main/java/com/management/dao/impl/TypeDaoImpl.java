package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.TypeDao;
import com.management.entities.Type;
import com.management.utils.MySQLConnectionUtils;

public class TypeDaoImpl implements TypeDao {

	/**
	 * 查询所有分类信息
	 */
	@Override
	public List<Type> queryAllType() throws SQLException {

		String sql = "SELECT * FROM type";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		List<Type> list = new LinkedList<Type>();

		while (res.next()) {
			Type type = new Type();
			type.setId(res.getInt(1));
			type.setName(res.getString(2));
			list.add(type);
		}

		return list;
	}

	/**
	 * 通过分类ID查询
	 */
	@Override
	public Type queryTypeById(Integer id) throws SQLException {

		String sql = "SELECT * FROM type WHERE id='" + id + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		Type type = new Type();

		while (res.next()) {
			type.setId(res.getInt(1));
			type.setName(res.getString(2));
		}

		return type;
	}

	/**
	 * 通过分类名查询
	 */
	@Override
	public Type queryTypeByName(String name) throws SQLException {

		String sql = "SELECT * FROM type WHERE name='" + name + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		Type type = new Type();

		while (res.next()) {
			type.setId(res.getInt(1));
			type.setName(res.getString(2));
		}
		return type;
	}

	/**
	 * 添加分类
	 */
	@Override
	public void addType(Type type) throws SQLException {

		String sql = "INSERT INTO `type` (`name`) VALUES (?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		String name = type.getName();

		ps.setString(1, name);

		ps.execute();
		
		ps.close();
		con.close();

	}

	/**
	 * 修改分类
	 */
	@Override
	public void alertType(Type type) throws SQLException {
		
		String sql = "UPDATE `type` SET `name`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		Integer id = type.getId();
		String name = type.getName();

		ps.setString(1, name);
		ps.setInt(2, id);

		ps.execute();
		
		ps.close();
		con.close();

	}

	/**
	 * 删除分类
	 */
	@Override
	public void deleteType(Integer id) throws SQLException {

		String sql = "DELETE FROM `type` WHERE (`id`='" + id + "')";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);
		
		statement.close();
		con.close();

	}

	/**
	 * 判断分类名字是否存在
	 */
	@Override
	public boolean existType(String typeName) throws SQLException {

		String sql = "SELECT * FROM type WHERE name='" + typeName + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		return res.next();
	}

}
