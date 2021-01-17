package com.management.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

/*
 * 对图片进行存放
 * @author ChenDanRun
 */

public class ImgUtils {
	//存放图片的地址
	static String filePath = "D:/Downloads/apache-tomcat-9.0.41-windows-x64/apache-tomcat-9.0.41/webapps/test2/target/test2/img";
	
	
	public static <T> T saveFile(HttpServletRequest request, Class<T> beanClass) throws Exception {
		
		File uploadPath = new File(filePath);
		int fileCount = 0;
		// 检查文件夹是否存在 不存在 创建一个
		if (!uploadPath.exists()) {
			// 创建文件夹
			uploadPath.mkdir();
		}
		List<String> img = new ArrayList<String>();
		// 文件最大容量 3M
		int fileMaxSize = 3 * 1024 * 1024;
		// 重命名策略
		FileRenameUtil rfrp = new FileRenameUtil();
		// 存放文件描述
		@SuppressWarnings("unused")
		String[] fileDiscription = { null, null };
		// 上传文件
		MultipartRequest mulit = new MultipartRequest(request, filePath, fileMaxSize, "UTF-8", rfrp);
		// 取得上传的所有文件(相当于标识)
		Enumeration filesname = mulit.getFileNames();
		while (filesname.hasMoreElements()) {
			String name = (String) filesname.nextElement();// 标识
			System.out.println(name);
			String fileName = mulit.getFilesystemName(name); // 取得文件名
			String contentType = mulit.getContentType(name);// 工具标识取得的文件类型
			if (contentType == null) { //未传入图片时默认设置的图片
				img.add(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/img/FFF_400x400.png");
			} else {
				img.add(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img/"
						+ fileName);
			}
			if (fileName != null) {
				fileCount++;
			}
			System.out.println("文件类型： " + contentType);
		}
		 System.out.println("共上传" + fileCount + "个文件！");   
		return WebUtils.multi2Bean(mulit, beanClass, img);
	}

}
