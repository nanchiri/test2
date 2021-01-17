package com.management.service;

import java.util.List;

import com.management.entities.User;
import com.management.exception.UserExistException;

public interface UserService {

	public void regiest(User user) throws UserExistException;

	public void deleteUser(Integer id);

	public Integer countAllUser();

	public boolean login(String user, String password);

	public List<User> getAllUser();

	public User searchUserById(Integer id);

	public User searchUserByAccount(String account);
	
	public boolean AccountIsExist(String account);
	
	public void updateUserPassword(Integer id, String password);

}
