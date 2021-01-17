package com.management.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 字符串和List的转换
 * 使用 | 分隔
 * @author ChenDanRun
 */

public class List2String {

	public static String list2String(List<String> list) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str += list.get(i)+"|";
		}
		return str;
	}

	public static List<String> string2List(String str) {
		String[] tmp = str.split("\\|");
		ArrayList< String> arrayList = new ArrayList<String>(tmp.length);
		Collections.addAll(arrayList, tmp);
		return arrayList;
	}
	
}
