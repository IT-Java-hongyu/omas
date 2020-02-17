package com.fosustu.omas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.pojo.Menu;
import com.fosustu.omas.pojo.User;
import com.fosustu.omas.service.MenuService;
import com.fosustu.omas.service.UserService;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 通过用户id获取用户菜单权限
	 * @param request
	 * @return
	 */
	
	@RequestMapping("getMenuTree")
	@ResponseBody
	public String getMenuTree(HttpServletRequest request) {
		Menu menu= userService.readMenusByUserid(getLoginUser(request).getUserid());
		System.out.println("menu String..." + menu.toString());
		String menuJson = JSON.toJSONString(menu ,true);
		//String newJson = menuJson.substring(1, menuJson.length()-1);
		
		//System.out.println("menuJSON" + newJson);
		return menuJson;
	}
	
	/**
	 *  获取当前登录用户
	 * @param request
	 * @return
	 */
	@RequestMapping("getLoginUser")
	@ResponseBody
	public User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("CURR_USER");
		return user;
	}
	
}
