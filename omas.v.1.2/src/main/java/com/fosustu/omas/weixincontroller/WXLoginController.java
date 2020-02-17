package com.fosustu.omas.weixincontroller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fosustu.omas.pojo.WXUser;
import com.fosustu.omas.service.WXLoginService;
import com.fosustu.omas.utils.AjaxReturnUtil;
import com.fosustu.omas.utils.JsonUtils;
import com.fosustu.omas.utils.TimeUtils;
import com.fosustu.omas.utils.UUIDUtils;
import com.fosustu.omas.utils.UrlUtil;
import com.fosustu.omas.utils.WXLoginUtils;

@Controller
@RequestMapping("wxLogin")
public class WXLoginController {

	@Autowired
	private WXLoginService _wxLoginService;
	
	/**
	 * 小程序微信登陆
	 * @param wxCode
	 * @return
	 */
	@RequestMapping(value="login",produces = "text/plain;charset=utf-8")
	public @ResponseBody String Login(@RequestBody String wxCode) {
		//请求微信api获取用户的openid和sessionKey
		JSONObject jsonObject = WXLoginUtils.getUserWXLoginInfo(wxCode);
		if(jsonObject!=null&&!jsonObject.containsKey("openid")) {
			return AjaxReturnUtil.ajaxReturn(false,"登陆失败"+jsonObject.get("errmsg")+jsonObject.get("errcode"));
		}
		String openid = (String)jsonObject.get("openid");
		String sessionKey = (String)jsonObject.get("session_key");
		//通过openid查询数据库是否有此用户
		List<WXUser> userList = _wxLoginService.getUserByOpenId(openid);
		//使用Map存储 message
		Map<String, Object> message = new HashMap<String, Object>();
//		JSONObject json=new JSONObject();
		if(userList!=null&&userList.size()!=0) {//用户已存在	

			WXUser wxUser=userList.get(0);
				
			int time = Integer.parseInt(TimeUtils.getDateDifference(wxUser.getTokenTime(), new Date()));
			if(time>60*60*24*3) {//过期
				wxUser.setToken(wxUser.getOpenid()+UUIDUtils.getId());
				wxUser.setTokenTime(new Date());
				_wxLoginService.updateTaken(wxUser);
			}else {
				wxUser.setTokenTime(new Date());
				_wxLoginService.updateTakenTime(wxUser);
			}
			message.put("patientId", userList.get(0).getPatientId());
			message.put("token",wxUser.getToken() );
		}else {
			WXUser wxUser = new WXUser();
				
			String patientId = UUIDUtils.getId(); //病人id
			String token=openid+UUIDUtils.getId();
			wxUser.setToken(token);
			wxUser.setPatientId(patientId);
			wxUser.setWxID(patientId);
			wxUser.setOpenid(openid);
			wxUser.setSessionKey(sessionKey);
			wxUser.setTokenTime(new Date());
			_wxLoginService.InsertWXUserMsg(wxUser);
			message.put("patientId", patientId);
			message.put("token",token);
		}
		return AjaxReturnUtil.ajaxReturn(true,message);
	}
	
	/**
	 * 小程序验证token是否过期
	 * @param token
	 * @return
	 */
	@RequestMapping(value="checkToken",produces = "text/plain;charset=utf-8")
	public @ResponseBody String CheckToken(@RequestBody String token) {		
		WXUser wxUser=_wxLoginService.getUserByToken(token);	
		if(wxUser ==null) {
			return AjaxReturnUtil.ajaxReturn(false,"token不存在，请重新登陆");
		}
		int time = Integer.parseInt(TimeUtils.getDateDifference(wxUser.getTokenTime(), new Date()));
		if(time>60*60*24*3) {//过期,就返回信息			
			return AjaxReturnUtil.ajaxReturn(false,"token过期，请重新登陆");
		}else {
			wxUser.setTokenTime(new Date());
			_wxLoginService.updateTakenTime(wxUser);
			Map<String, Object> message = new HashMap<String,Object>();
//			JSONObject json=new JSONObject();
			message.put("token",token);
			return AjaxReturnUtil.ajaxReturn(true,message);
		}		
	}
	
	@RequestMapping(value="returnMessage",produces = "text/plain;charset=utf-8")
	public @ResponseBody String returnMessage() {	
		return AjaxReturnUtil.ajaxReturn(false,"token不存在，请先登录！");
	}
	@RequestMapping(value="returnMessageTwo",produces = "text/plain;charset=utf-8")
	public @ResponseBody String returnMessageTwo() {	
		return AjaxReturnUtil.ajaxReturn(false,"token过期，请重新登录！");
	}

}
