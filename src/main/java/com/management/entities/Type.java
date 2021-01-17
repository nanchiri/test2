package com.management.entities;

import java.util.HashMap;
import java.util.Map;

/*
 * 实体类：分类
 */

public class Type {

	private Integer id;
	private String name;
	private Map<String, String> errors = new HashMap<String, String>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	

}
