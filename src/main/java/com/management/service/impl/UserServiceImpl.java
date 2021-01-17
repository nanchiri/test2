package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.UserDao;
import com.management.dao.impl.UserDaoImpl;
import com.management.entities.User;
import com.management.exception.UserExistException;
import com.management.service.UserService;
import com.management.utils.CheckDataValidity;
import com.management.utils.PasswordEncryptionUtils;

public class UserServiceImpl implements UserService {

	UserDao dao = new UserDaoImpl();

	/*
	 * 新增用户
	 */
	@Override
	public void regiest(User user) throws UserExistException {

		if (user == null) {
			return;
		}
		try {
			if (dao.existUser(user)) {// 判断用户名是否存在
				user.getError().put("user", "用户名已存在");
				throw new UserExistException();
			} else {
				if (CheckDataValidity.userValidate(user)) {
					// 判断输入的信息合法性
					user.setPassword(PasswordEncryptionUtils.plainText2MD5Encrypt(user.getPassword()));
					// 密码加密提交
					dao.addUser(user);
				}else {
					throw new UserExistException();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(Integer id) {

		try {
			dao.deleteUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 查询用户数量
	 */
	@Override
	public Integer countAllUser() {

		int count = 0;

		try {
			List<User> list = dao.queryAllUser();
			count = list.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean login(String user, String password) {
		String encryptionPassword = PasswordEncryptionUtils.plainText2MD5Encrypt(password);
		try {
			boolean result = dao.loginUser(user, encryptionPassword);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> getAllUser() {

		List<User> list = null;
		try {
			list = dao.queryAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User searchUserById(Integer id) {
		User user = null;
		if (id == null) {
			return null;
		}
		try {
			user = dao.queryUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User searchUserByAccount(String account) {
		User user = null;
		if (account == null) {
			return null;
		}
		try {
			user = dao.queryUserByAccount(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/*
	 * 查询指定用户姓名是否存在
	 */
	@Override
	public boolean AccountIsExist(String account) {
		User user = new User();
		boolean res = true;
		user.setAccount(account);
		try {
			res = dao.existUser(user);
		} catch (UserExistException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void updateUserPassword(Integer id, String password) {

		if(id == null || password == null){
			return;
		}
		try {
			dao.alertUserPassword(id, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
