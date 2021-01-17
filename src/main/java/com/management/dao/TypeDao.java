package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Type;

public interface TypeDao {
	
	public List<Type> queryAllType() throws SQLException;
	
	public Type queryTypeById(Integer id) throws SQLException;
	
	public Type queryTypeByName(String name) throws SQLException;
	
	public void addType(Type type) throws SQLException;
	
	public void alertType(Type type) throws SQLException;
	
	public void deleteType(Integer id) throws SQLException;
	
	public boolean existType(String typeName) throws SQLException;
	

}
