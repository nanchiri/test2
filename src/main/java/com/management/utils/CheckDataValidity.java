package com.management.utils;

import com.management.entities.Administrator;
import com.management.entities.Goods;
import com.management.entities.Type;
import com.management.entities.User;

/**
 * 提交信息合法性检查
 * @author CheungChingYin
 *
 */
public class CheckDataValidity {

	/**
	 * 检查管理员数据合法性
	 * @param admin
	 * @return
	 */
	public static boolean administratorValidate(Administrator admin) {
		boolean isOk = true;
		// 1.用户名不能为空
		if (admin.getUser() == null || admin.getUser().trim().equals("")) {
			isOk = false;
			admin.getError().put("user", "用户名不能为空");
		}
		// 2.密码需要为字母数字混合,限制为6-14位
		if (admin.getPassword() == null || admin.getPassword().trim().equals("")) {
			isOk = false;
			admin.getError().put("password", "密码不能为空");
		} else if (!admin.getPassword().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$")) {

			/*
			 * (?![0-9]+$) 预测该位置后面不全是数字 (?![a-zA-Z]+$) 预测该位置后面不全是字母 [0-9A-Za-z]
			 * {8,16} 由8-16位数字或这字母组成
			 *
			 * 参考地址：https://blog.csdn.net/u011974797/article/details/71479456
			 */
			isOk = false;
			admin.getError().put("password", "密码需要为字母数字混合,限制为6-14位");
		}
		// 管理员权限必须为数字且不能为空
		if (admin.getPermission() == null) {
			isOk = false;
			admin.getError().put("permission", "管理员权限不能为空");
		} else if (admin.getPermission().toString().matches("[0-1]{1,1}"))
			;
		return isOk;
	}
	
	/**
	 * 检查用户数据合法性
	 * @param user
	 * @return
	 */
	public static boolean userValidate(User user) {
		boolean isOk = true;
		// 1.用户名不能为空
		if (user.getAccount() == null || user.getAccount().trim().equals("")) {
			isOk = false;
			user.getError().put("user", "用户名不能为空");
		}
		// 2.密码需要为字母数字混合,限制为6-14位
		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			isOk = false;
			user.getError().put("password", "密码不能为空");
		} else if (!user.getPassword().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$")) {
			isOk = false;
			user.getError().put("password", "密码需要为字母数字混合,限制为6-14位");
		}
		
		return isOk;
	}
	
	/**
	 * 检查商品数据合法性
	 * @param goods
	 * @return
	 */
	public static boolean checkGoodsValidate(Goods goods) {

		boolean isOk = true;

		if (goods.getName() == null || goods.getName().trim().equals("")) {
			System.out.println("111");
			isOk = false;
		}
		if (goods.getType_id() == null || goods.getType_id().toString().equals("")) {
			System.out.println("333");
			isOk = false;
		} else if (!goods.getType_id().toString().matches("[0-9]+")) {
			System.out.println("444");
			isOk = false;
		}

		return isOk;
	}

	/**
	 * 检查分类数据合法性
	 * @param type
	 * @return
	 */
	public static boolean checkTypeValidate(Type type) {

		boolean isOk = true;

		if (type.getName() == null || type.getName().trim().equals("")) {
			type.getErrors().put("name", "用户名不能为空！");
			isOk = false;
		} else if (!type.getName().matches("^[\u4e00-\u9fa5_a-zA-Z]+$")){
			type.getErrors().put("name", "用户名必须为中文或者是英文");
			isOk = false;
		}
		return isOk;
	}
	
}
