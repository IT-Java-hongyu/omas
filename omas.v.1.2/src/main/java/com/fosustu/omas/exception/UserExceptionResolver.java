package com.fosustu.omas.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * @author Administrator
 *
 */
public class UserExceptionResolver implements HandlerExceptionResolver {

	@ResponseBody
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		//1.解析出异常类型
		UserException userException = null;
		if(ex instanceof UserException) {
			/**
			 * 2.如果该异常类型为系统自定义异常，直接取出异常信息，在错误页面展示
			 */
			userException = (UserException)ex;
		}else {
			/**
			 * 3.如果该异常类型不是系统自定义的异常，
			 * 构造一个自定义异常类型（信息为“未知错误”）
			 */
			userException = new UserException("未知错误");
		}
		
		//错误信息
		String message = userException.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		//指向错误页面
		modelAndView.setViewName("errorPage");
		return modelAndView;
	}

}
