package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.User;
import com.management.exception.UserExistException;

public interface UserDao {

	public boolean existUser(User user) throws UserExistException,SQLException;
	
	public boolean loginUser(String userName, String password) throws SQLException;
	
	public void addUser(User user) throws SQLException;
	
	public List<User> queryAllUser() throws SQLException;
	
	public User queryUserById(Integer id) throws SQLException;
	
	public void deleteUser(Integer id) throws SQLException;
	
	public User queryUserByAccount(String account) throws SQLException;
	
	public void alertUserPassword(Integer id, String password) throws SQLException;
	
}
