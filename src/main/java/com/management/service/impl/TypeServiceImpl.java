package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.TypeDao;
import com.management.dao.impl.TypeDaoImpl;
import com.management.entities.Type;
import com.management.service.TypeService;

public class TypeServiceImpl implements TypeService {

	private TypeDao dao = new TypeDaoImpl();

	/*
	 * 搜索全部分类
	 */
	@Override
	public List<Type> searchAllType() {

		List<Type> list = null;
		try {
			list = dao.queryAllType();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 通过ID搜索分类
	 */
	@Override
	public Type searchTypeById(Integer id) {

		Type Type = null;
		try {
			Type = dao.queryTypeById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Type;
	}

	/*
	 * 通过名字搜索分类
	 */
	@Override
	public Type searchTypeByName(String name) {

		Type Type = null;
		try {
			Type = dao.queryTypeByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Type;
	}

	/*
	 * 新增分类
	 */
	@Override
	public void RegistType(Type Type) {
		try {
			dao.addType(Type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 修改分类
	 */
	@Override
	public void AlertType(Type type) {

		try {
			dao.alertType(type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 删除分类
	 */
	@Override
	public void deleteType(Integer id) {
		try {
			dao.deleteType(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
