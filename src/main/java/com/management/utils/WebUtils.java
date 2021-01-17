package com.management.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.oreilly.servlet.MultipartRequest;

/**
 * 网页工具
 */
public class WebUtils {

	/**
	 * request内容装载到实体类中
	 * @param request
	 * @param beanClass
	 * @return
	 */
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		// 创建要封装数据的bean
		try {
			T bean = beanClass.newInstance();
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String value = "";
				value = request.getParameter(name);
				if (!value.equals("") && value != null) {
					BeanUtils.setProperty(bean, name, value);
				}
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * multi内容装载到实体类中
	 * 因为文件获取不到Parameter 所以得单独拿出来存入
	// * @param request
	 * @param beanClass
	 * @return
	 */
	public static <T> T multi2Bean(MultipartRequest multi, Class<T> beanClass,List<String> list) {
		// 创建要封装数据的bean
		try {
			T bean = beanClass.newInstance();
			@SuppressWarnings("unchecked")
			Enumeration<String> e = multi.getParameterNames();
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String value = "";
				value = multi.getParameter(name);
				if (!value.equals("") && value != null) {
					BeanUtils.setProperty(bean, name, value);
				}
			}
			BeanUtils.setProperty(bean, "img", list);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 复制Bean
	 * @param src
	 * @param dest
	 */
	public static void copyBean(Object src, Object dest) {
		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object value) {// 注册一个covert转换器因为copyProperties只支持8种基本数据类型，不支持Date类型
				if (value == null) {
					return null;
				}
				String str = (String) value;
				if (str.trim().equals("")) {
					return null;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//产生全球唯一的ID
	public static String generateID(){
		return UUID.randomUUID().toString();
	}
}
