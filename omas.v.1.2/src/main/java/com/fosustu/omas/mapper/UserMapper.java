package com.fosustu.omas.mapper;

import java.util.List;
import java.util.Map;

import com.fosustu.omas.pojo.Menu;
import com.fosustu.omas.pojo.Role;
import com.fosustu.omas.pojo.User;

public interface UserMapper {
	
	List<User> findByUsernameAndPwd(Map map);     //通过用户名和密码获取用户
	void updatePassword(String id,String newpwd);  //修改密码
	List<User> getUserList();                      //获取用户列表
	List<Role> getRolesbyUserId(String id);        //通过用户id获取角色
	List<Role> getAllRole();                       //获取所有角色
    User selectByPrimaryKey(String userid);        //通过主键获取用户
    Role getRole(String id);                       //通过id获取角色
    Menu getMenu(String id);                       //通过id获取菜单
    List<Role> getAllRoles(String id);             //获取所有角色
    List<Menu> getAllMenus(String id);             //获取所有菜单
    User getRoleAndMenuByUser(String id);          //通过用户id获取角色和菜单
   
    
}