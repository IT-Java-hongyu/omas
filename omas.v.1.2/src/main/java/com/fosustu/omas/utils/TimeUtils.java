package com.fosustu.omas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	//判断日期是否相同
	public boolean Compare(Date time) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(time);
		String now = sdf.format(new Date()) ;
		boolean result = false;
		if(now.equals(date)) {
			result=true;
		}
		return result;
	}
	
	//把字符串的Date转换成Date
	
	/**
	 * 计算两个日期差值
	 */
	public static String getDateDifference(Date startDate,Date endDate) {
		long different = endDate.getTime()-startDate.getTime();//精确到微秒
		different /= 1000; //精确到秒
		return different >=0?String.valueOf(different):"0";
		
	}
	
	
	 

}
