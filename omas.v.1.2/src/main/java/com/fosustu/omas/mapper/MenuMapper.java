package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.Menu;

public interface MenuMapper {
	List<Menu> getMenuTree(String pid);     //获取菜单树
	Menu getMenu(String pid);               //获取菜单
	Menu selectSelfAndChildByParentId(String pid); //通过父id获取所有menu
}