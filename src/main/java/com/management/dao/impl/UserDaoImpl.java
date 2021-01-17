package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.UserDao;
import com.management.entities.User;
import com.management.exception.UserExistException;
import com.management.utils.MySQLConnectionUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * 查询账号是否存在
	 */
	@Override
	public boolean existUser(User user) throws UserExistException, SQLException {

		String userName = user.getAccount();
		String sql = "SELECT * FROM user WHERE account='" + userName + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
		
	}

	/**
	 * 登录查询
	 */
	@Override
	public boolean loginUser(String userName, String password) throws SQLException {

		String sql = "SELECT * FROM user WHERE account='" + userName + "' AND password='" + password + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	/**
	 * 添加用户信息
	 */
	@Override
	public void addUser(User user) throws SQLException {

		String sql = "INSERT INTO `user` (`account`, `password`) VALUES (?,?)";
		PreparedStatement ps = null;
		Connection con = MySQLConnectionUtils.mySQLConnection();
		ps = con.prepareStatement(sql);

		ps.setString(1, user.getAccount());
		ps.setString(2, user.getPassword());

		ps.execute();

		ps.close();
		con.close();

	}

	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<User> queryAllUser() throws SQLException {

		String sql = "SELECT * FROM user";
		List<User> list = new LinkedList<User>();
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		while (result.next()) {
			User user = new User();
			user.setId(result.getInt(1));
			user.setAccount(result.getString(2));
			list.add(user);
		}
		return list;
	}

	/**
	 * 通过用户ID查询
	 */
	@Override
	public User queryUserById(Integer id) throws SQLException {

		String sql = "SELECT * FROM user WHERE id='" + id + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		User user = new User();
		while (result.next()) {
			user.setId(result.getInt(1));
			user.setAccount(result.getString(2));
		}
		return user;
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public void deleteUser(Integer id) throws SQLException {

		String sql = "DELETE FROM `user` WHERE (`id`='" + id + "')";

		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);

		statement.close();
		con.close();

	}

	/**
	 * 通过用户账号查询
	 */
	@Override
	public User queryUserByAccount(String account) throws SQLException {
		String sql = "SELECT * FROM user WHERE account='" + account + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		User user = new User();
		while (result.next()) {
			user.setId(result.getInt(1));
			user.setAccount(result.getString(2));
		}
		return user;
	}

	/**
	 * 修改管理员密码
	 */
	@Override
	public void alertUserPassword(Integer id, String password) throws SQLException {

		String sql = "UPDATE `user` SET `password` = ? WHERE (`id` = ?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, password);
		ps.setInt(2, id);
		ps.execute();

		ps.close();
		con.close();
	}

}
