package com.fosustu.omas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.MenuMapper;
import com.fosustu.omas.pojo.Menu;
import com.fosustu.omas.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	/**
	 * 获取所有菜单列表
	 */
	@Override
	public Menu getMenuTree(String Pid) {
		return menuMapper.selectSelfAndChildByParentId("0");
	}

}
