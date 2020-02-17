package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.RoleMenu;

public interface RoleMenuMapper {
    void addRoleMenu(RoleMenu roleMenu);       //新增角色菜单
    void deleteRoleMenuById(String roleId);    //删除角色菜单
}