package com.fosustu.omas.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WXLoginUtils {
	
	/**
	 * 获取openid
	 * @param wxCode
	 * @return
	 */
	public static JSONObject getUserWXLoginInfo(String wxCode) {
		String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String,String> requestUrlParam = new HashMap<String,String>();
		requestUrlParam.put("appid", "wxf5ac78c38d08d10e");	//开发者设置中的appId
		requestUrlParam.put("secret", "4cbf2d7347c7a02b06a7c5e1bf263a95");	//开发者设置中的appSecret
		requestUrlParam.put("js_code", wxCode);	//小程序调用wx.login返回的code
		requestUrlParam.put("grant_type", "authorization_code");	//默认参数
		//发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
		JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, requestUrlParam));
		return jsonObject;
	}


}
