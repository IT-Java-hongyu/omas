package com.fosustu.omas.utils;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSON;

public class AjaxReturnUtil {
	/**
	 * 返回前端操作结果
	 * @param success
	 * @param message
	 */
	
	public static String ajaxReturn(boolean success, String message){
		//返回前端的JSON数据
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return JSON.toJSONString(rtn);
	}
	
	public static String ajaxReturn(boolean success, Map<String,Object> message){
		//返回前端的JSON数据
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		return JSON.toJSONString(rtn);
	}
}
