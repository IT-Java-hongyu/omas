package com.fosustu.omas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.exception.UserException;
import com.fosustu.omas.pojo.Role;
import com.fosustu.omas.pojo.Tree;
import com.fosustu.omas.pojo.User;
import com.fosustu.omas.service.UserService;
import com.fosustu.omas.utils.AjaxReturnUtil;

@Controller

public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 *   用户登录类
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 * @throws UserException
	 */
	@ResponseBody
	@RequestMapping("loginCtrl")
	public Map<String , Object> UserLogin(User user , Model model , HttpServletRequest request) throws UserException {
		//if(user.getUsername().contains("ad"))throw new UserException("登录异常");
		Map<String , String> map = new HashMap<>();
		
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		List<User> loginUser = userService.findByUsernameAndPwd(map);
		Map<String , Object> map2 = new HashMap<>();
		if(loginUser != null && loginUser.size() > 0) {
        	request.getSession().setAttribute("CURR_USER", loginUser.get(0));
            System.out.println(loginUser.get(0).toString() + "----------------");
            map2.put("code", "0");
        }else {
        	map2.put("code", "1");
        	map2.put("msg", "登录名或密码错误!");
        }
		
		
		return map2;
	}
	
	
	/*
	 * 获取用户列表
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	public String getUserList() {
		List<User> userList = userService.getUserList();
		System.out.println(userList.toString());
		return JSON.toJSONString(userList, true);
	}
	
	/**
	 *  通过用户id查找用户
	 * @param id
	 * @return
	 */
	@RequestMapping("getUserById")
	@ResponseBody
	public String getUserById(String id) {
		User user = userService.getUserById(id);
		System.out.println(user.toString());
		return user.toString();
	}
	
	
	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	public User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("CURR_USER");
		return user;
	}
	
	/**
	 * 获取当前登录用户名
	 * @param request
	 * @return
	 */
	@RequestMapping("getLoginUserName")
	@ResponseBody
	public String getLoginUserName(HttpServletRequest request) {
		return AjaxReturnUtil.ajaxReturn(true, getLoginUser(request).getUsername());
		
	}
	/**
	 * 退出登陆
	 */
	@RequestMapping("loginOut")
	@ResponseBody
	public String loginOut(HttpServletRequest request){
		request.getSession().removeAttribute("CURR_USER");
		return AjaxReturnUtil.ajaxReturn(true, "退出成功");
	}
	/**
	 * 修改密码
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public String updatePassword(HttpServletRequest request , String newPass){
		User loginUser = getLoginUser(request);
		//session是否会超时，用户是否登陆过了
		if(null == loginUser){
			return AjaxReturnUtil.ajaxReturn(false, "亲，您还没有登陆");
			
		}
		
		userService.updatePassword(loginUser.getUserid(),newPass);
		return AjaxReturnUtil.ajaxReturn(true, newPass);
	}
	
	
	@RequestMapping("getUserAndRoleById")
	@ResponseBody
	public String getUserAndRoleById(String id) {
		List<Tree> t = userService.readUserRoles(id);
		return JSON.toJSONString(t, true);
	}
	
	@RequestMapping("updateUserRoles")
	@ResponseBody
	public String updateUserRoles(String id, String checkedStr) {
		try {
			System.out.println(id+"************updateUserRoles************" +checkedStr);
			userService.updateUserRoles(id, checkedStr);
			System.out.println("************updateRoleMenus************" );
			return AjaxReturnUtil.ajaxReturn(true, "设置成功");
		}catch(Exception e) {

			return AjaxReturnUtil.ajaxReturn(false, "设置失败");
		}
		
	}
	
	@RequestMapping("getRoleAndMenuById")
	@ResponseBody
	public String getRoleAndMenuById(String id) {
		List<Tree> t = userService.readRoleMenus(id);
		return JSON.toJSONString(t, true);
	}
	@RequestMapping("getRoleList")
	@ResponseBody
	public String getRoleList() {
		List<Role> t = userService.getRoleList();
		return JSON.toJSONString(t, true);
	}
	
	@RequestMapping("updateRoleMenus")
	@ResponseBody
	public String updateRoleMenus(String id, String checkedStr) {
		try {
			System.out.println(id+"************updateRoleMenus************" +checkedStr);
			userService.updateRoleMenus(id, checkedStr);
			System.out.println("************updateRoleMenus************" );
			return AjaxReturnUtil.ajaxReturn(true, "设置成功");
		}catch(Exception e) {

			return AjaxReturnUtil.ajaxReturn(false, "设置失败");
		}
		
	}
}
