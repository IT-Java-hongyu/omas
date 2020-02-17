package com.fosustu.omas.test;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.fosustu.omas.pojo.User;

public class UserMapperTest {
	public DataConnection dataConn = new DataConnection();
	
	@Test
	public void testSelect() throws IOException{
		SqlSession sqlSession = dataConn.getSqlSession();
		User user = sqlSession.selectOne("com.fosustu.omas.mapper.UserMapper.getUserById",1);
		System.out.println(user);
		sqlSession.close();
	}
}
