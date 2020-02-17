package com.fosustu.omas.service;

import java.util.List;
import java.util.Map;

import com.fosustu.omas.pojo.Menu;
import com.fosustu.omas.pojo.Role;
import com.fosustu.omas.pojo.Tree;
import com.fosustu.omas.pojo.User;

public interface UserService {
	User getUserById(String id);       //通过id获取用户
	void updatePassword(String id , String newpwd); //通过id更改密码
	List<User> getUserList();                       //获取所有用户列表
	List<User> findByUsernameAndPwd(Map map);       //通过用户名和密码查找用户
	
	User getRoleAndMenuById(String id);             //通过用户id获取用户的角色和菜单
	//List<Role> getAllRoles(String id);     
	List<Tree> readRoleMenus(String id);            // 通过用户id获取用户的角色和菜单的树列表      
	
	List<Role> getRoleList();                       //获取角色列表
	public List<Tree> readUserRoles(String id);     //通过用户id获取用户角色树
	public void updateUserRoles(String userid, String checkedStr);  //更改用户角色
	
	public void updateRoleMenus(String roleid, String checkedStr);  //更改角色菜单
	public Menu readMenusByUserid(String id);                       //通过用户id获取用户菜单列表
}
