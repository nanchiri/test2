package com.management.service;

import java.util.List;

import com.management.entities.Type;

public interface TypeService {

	public List<Type> searchAllType();
	
	public Type searchTypeById(Integer id);
	
	public Type searchTypeByName(String name);
	
	public void RegistType(Type type);
	
	public void AlertType(Type type);
	
	public void deleteType(Integer id);
}
