package com.management.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;


/*
 * 对文件进行重命名
 * @author ChenDanRun
 */

public class FileRenameUtil implements FileRenamePolicy{

	@Override
	public File rename(File file) {
		// TODO Auto-generated method stub
		String body = "";
		String ext = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		
		int pot = file.getName().lastIndexOf(".");//取得文件名和后缀分割点 
		if (pot != -1) {//说明后缀存在
			body = date.getTime() + "";//采用时间的形式命名
			body = sdf.format(date);
			ext = file.getName().substring(pot);//截取后缀名
		}else {
			body = (new Date()).getTime() + "";
			ext = "";
		}
		Random rand = new Random();
		int rnd = Math.abs(rand.nextInt(9000))+1000;
		String newName = body +rnd + ext;
		file = new File(file.getParent(), newName);//对文件进行重命名
		return file;
	}

	
	
}
