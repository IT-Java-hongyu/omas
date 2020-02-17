package com.fosustu.omas.weixincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosustu.omas.pojo.User;
import com.fosustu.omas.service.UserService;

@Controller
@RequestMapping("winXinUser")
public class WinXinUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("getUserById")
	@ResponseBody
	public String getUserById(String id) {
		User user = userService.getUserById(id);
		System.out.println(user.toString());
		return user.toString();
	}
}
