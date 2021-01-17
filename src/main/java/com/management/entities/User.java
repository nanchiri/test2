package com.management.entities;

import java.util.HashMap;
import java.util.Map;

/*
 * 实体类：用户
 */

public class User {

	private Integer id;
	private String account;
	private String password;
	private Map<String, String> error = new HashMap<String, String>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getError() {
		return error;
	}

	public void setError(Map<String, String> error) {
		this.error = error;
	}

	
}
