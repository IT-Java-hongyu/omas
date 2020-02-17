package com.fosustu.omas.interceptor;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fosustu.omas.pojo.WXUser;
import com.fosustu.omas.service.WXLoginService;
import com.fosustu.omas.utils.AjaxReturnUtil;
import com.fosustu.omas.utils.TimeUtils;

public class LoginInterceptor implements HandlerInterceptor{

	@Autowired
	private WXLoginService _wxLoginService;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String uri = request.getRequestURI();
		response.setContentType("text/html;charset=utf-8");
		//判断当前请求地址是否是登录地址
		if(uri.contains("wx"))return true;
		if(!(uri.contains("Login")||uri.contains("login"))) {
			if(uri.contains("wx")) {
				if(uri.contains("wxLogin"))
					return true;
				else {
					String token= request.getHeader("token");
					if(token==null) {
						response.sendRedirect(request.getContextPath() + "/wxLogin/returnMessage");
					}
					WXUser wxUser=_wxLoginService.getUserByToken(token);
					if(wxUser ==null) {
						response.sendRedirect(request.getContextPath() + "/wxLogin/returnMessage");
					}
					int time = Integer.parseInt(TimeUtils.getDateDifference(wxUser.getTokenTime(), new Date()));
					if(time>60*60*24*3) {//过期,就返回信息
						response.sendRedirect(request.getContextPath() + "/wxLogin/returnMessageTwo");
					}else {
						wxUser.setTokenTime(new Date());
						_wxLoginService.updateTakenTime(wxUser);
						return true;
					}
				}
			}
			
			//非登录请求
			if(request.getSession().getAttribute("CURR_USER") != null) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}else {
			//登录请求直接放行
			return true;
		}
		return false; //默认拦截
	}
	
}
