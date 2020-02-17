package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.UserRole;

public interface UserRoleMapper {
   void addUserRole(UserRole userRole); //添加用户角色
   void deleteUserRole(String userId);  //删除用户角色 
}