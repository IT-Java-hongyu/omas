package com.fosustu.omas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosustu.omas.mapper.MenuMapper;
import com.fosustu.omas.mapper.RoleMapper;
import com.fosustu.omas.mapper.RoleMenuMapper;
import com.fosustu.omas.mapper.UserMapper;
import com.fosustu.omas.mapper.UserRoleMapper;
import com.fosustu.omas.pojo.Menu;
import com.fosustu.omas.pojo.Role;
import com.fosustu.omas.pojo.RoleMenu;
import com.fosustu.omas.pojo.Tree;
import com.fosustu.omas.pojo.User;
import com.fosustu.omas.pojo.UserRole;
import com.fosustu.omas.service.UserService;





@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired 
	private MenuMapper menuMapper;
	
	@Autowired 
	private RoleMenuMapper roleMenuMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	/**
	 * 通过id获取用户
	 */
	@Override
	public User getUserById(String id) {
		
		return userMapper.selectByPrimaryKey(id);
	}
	/**
	 * 通过用户名和密码查找用户
	 */
	@Override
	public List<User> findByUsernameAndPwd(Map map) {
		return userMapper.findByUsernameAndPwd(map);
	}

	/**
	 * 通过用户id获取用户角色和菜单
	 */
	@Override
	public User getRoleAndMenuById(String id) {
		User user = userMapper.getRoleAndMenuByUser(id);
		System.out.println(user.toString());
		
		
		
		return user;
	}
	
	
	
	/**
	 * 获取用户角色
	 * @param uuid
	 * @return
	 */
	public List<Tree> readUserRoles(String id){
		List<Tree> treeList = new ArrayList<Tree>();
		//获取用户信息
		//User user = userMapper.getRoleAndMenuByUser(id);
		
		//获取用户下的角色列表
		List<Role> userRoles = userMapper.getRolesbyUserId(id);
		//获取所有角色列表
		List<Role> rolesList = userMapper.getAllRole();
		Tree t1 = null;
		for(Role role : rolesList){
			t1 = new Tree();
			//转换成string类型
			t1.setId(String.valueOf(role.getRoleid()));
			t1.setText(role.getName());
			//判断是否需要勾选中,用户是否拥有这个角色
			System.out.println("userRoles.toString()**********"+userRoles.toString());
			System.out.println(role.toString() + "************************");
			if(userRoles.toString().contains(role.getName())){
				t1.setChecked(true);
			}
			treeList.add(t1);
		}
		return treeList;
	}
	
	/**
	 * 更新用户的角色
	 * @param uuid
	 * @param checkedStr
	 */
	
	public void updateUserRoles(String userid, String checkedStr){
		//清除角色下的菜单权限, delete from user_role where userid=?
		userRoleMapper.deleteUserRole(userid);
		
		
		String[] ids = checkedStr.split(",");
		System.out.println(ids.length +" *********");
		UserRole userRole  = null;
		for(String id : ids){
			userRole = new UserRole();
			userRole.setUserid(userid);
			userRole.setRole(id);
			System.out.println("**********" + userRole.toString());
			//保存用户下的角色权限 insert into user_role;
			userRoleMapper.addUserRole(userRole);
		}
	}
	
	
	
	
	
	
	
		/**
		 * 获取角色菜单权限
		 * @param uuid 角色编号
		 */
		public List<Tree> readRoleMenus(String id){
			User user = userMapper.getRoleAndMenuByUser(id);
			System.out.println("user.toString()*****"+user.toString());
			List<Tree> treeList = new ArrayList<Tree>();
			//获取角色信息
			Role role = userMapper.getRole(id);
			System.out.println(role.toString());
			//获取角色菜单
			List<Menu> roleMenus = role.getMenus();
			System.out.println("roleMenus************" + roleMenus.toString());
			//根菜单
			Menu root = menuMapper.getMenu("0");
			System.out.println("root"+root.getMenus().toString());
			Tree t1 = null;
			Tree t2 = null;
			//一级菜单
			for(Menu m : root.getMenus()){
				t1 = new Tree();
				t1.setId(m.getMenuid());
				t1.setText(m.getMenuname());
				//二级菜单
				for(Menu m2 : m.getMenus()){
					t2 = new Tree();
					t2.setId(m2.getMenuid());
					t2.setText(m2.getMenuname());
					//如果角色下包含有这个权限菜单，让它勾选上
					System.out.println("roleMenus.contains(m2)"+ roleMenus.contains(m2));
					if(roleMenus.toString().contains(m2.getMenuid())){
						System.out.println("****************--------------------*******************");
						t2.setChecked(true);
					}
					t1.getChildren().add(t2);
				}
				treeList.add(t1);
			}
			return treeList;
		}

		/**
		 * 更新角色权限
		 * @param uuid 角色编号
		 * @param checkedStr 勾选中菜单的ID字符串，以逗号分割
		 */
		public void updateRoleMenus(String roleid, String checkedStr){
			//清除角色下的菜单权限, delete from role_menu where roleuuid=?
			roleMenuMapper.deleteRoleMenuById(roleid);
			
			
			String[] ids = checkedStr.split(",");
			System.out.println(ids.length +" *********");
			RoleMenu roleMenu  = null;
			for(String id : ids){
				roleMenu = new RoleMenu();
				roleMenu.setRoleid(roleid);
				roleMenu.setMenuid(id);
				System.out.println("**********" + roleMenu.toString());
				//保存角色下的菜单权限 insert into role_menu;
				roleMenuMapper.addRoleMenu(roleMenu);
			}
		}
		
		/**
		 * 获取角色列表
		 */
		@Override
		public List<Role> getRoleList() {
			// TODO Auto-generated method stub
			return roleMapper.getRoleList();
		}
		/**
		 * 获取用户列表
		 */
		@Override
		public List<User> getUserList() {
			// TODO Auto-generated method stub
			return userMapper.getUserList();
		}
		/**
		 * 通过用户id获取菜单权限
		 */
		@Override
		public Menu readMenusByUserid(String id) {
			System.out.println("_______*****_____" + id);
			//获取所有的菜单，做模板用的
			Menu root = menuMapper.selectSelfAndChildByParentId("0");
			//用户下的菜单集合
			List<Menu> userMenus = userMapper.getAllMenus(id);
			System.out.println(root.getMenus().toString());
			//根菜单
			Menu menu = cloneMenu(root);
			
			System.out.println("++++++menu+++++++" + menu.toString());
			//循环匹配模板
			//一级菜单
			Menu _m1 = null;
			Menu _m2 = null;
			for(Menu m1 : root.getMenus()){
				_m1 = cloneMenu(m1);
				//二级菜单循环
				for(Menu m2 : m1.getMenus()){
					//用户包含有这个菜单
					if(userMenus.toString().contains(m2.getMenuid())){
						//复制菜单
						_m2 = cloneMenu(m2);
						//加入到上级菜单下
						_m1.getMenus().add(_m2);
					}
				}
				//有二级菜单我们才加进来
				if(_m1.getMenus().size() > 0){
					//把一级菜单加入到根菜单下
					menu.getMenus().add(_m1);
				}
			}
			
			System.out.println("menureturn ++++++++++++++++" + menu.toString());
			return menu;
		}
		
		/**
		 * 复制menu
		 * @param src
		 * @return
		 */
		private Menu cloneMenu(Menu src){
			Menu menu = new Menu();
			menu.setIcon(src.getIcon());
			menu.setMenuid(src.getMenuid());
			menu.setMenuname(src.getMenuname());
			menu.setUrl(src.getUrl());
			menu.setMenus(new ArrayList<Menu>());
			return menu;
		}

		@Override
		public void updatePassword(String id, String newpwd) {
			userMapper.updatePassword(id,newpwd);
		}
		

}
