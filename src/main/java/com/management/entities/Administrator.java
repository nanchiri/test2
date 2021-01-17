package com.management.entities;

import java.util.HashMap;
import java.util.Map;

/*
 * 实体类：管理员
 */

public class Administrator {

	private Integer id;
	private String user;
	private String password;
	private String data;
	private Integer permission;
	private Integer typeManager;
	private Integer goodsManager;
	private Integer orderManager;
	private Integer userManager;
	private Map<String, String> error = new HashMap<String, String>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getTypeManager() {
		return typeManager;
	}

	public void setTypeManager(Integer typeManager) {
		this.typeManager = typeManager;
	}

	public Integer getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(Integer goodsManager) {
		this.goodsManager = goodsManager;
	}

	public Integer getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(Integer orderManager) {
		this.orderManager = orderManager;
	}

	public Integer getUserManager() {
		return userManager;
	}

	public void setUserManager(Integer userManager) {
		this.userManager = userManager;
	}

	public Map<String, String> getError() {
		return error;
	}

	public void setError(Map<String, String> error) {
		this.error = error;
	}

	
}
