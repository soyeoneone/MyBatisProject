package com.lsp.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lsp.dao.UserDAO;
import com.lsp.entity.User;

public class UserDAOTest {
	private static SqlSessionFactory sqlSessionFactory;
	
	@BeforeClass
	public static void init(){
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void selectUserById(){
		SqlSession session = sqlSessionFactory.openSession();
		//User user = session.selectOne("com.lsp.dao.UserDAO.selectUser", 1);
		UserDAO udao = session.getMapper(UserDAO.class);
		User user = udao.selectUser(1);
		System.out.println(user.getName()+"  "+user.getPassword());
		session.close();
	}
	
	@Test
	public void selectAllUser(){
		SqlSession session = sqlSessionFactory.openSession();
		UserDAO udao = session.getMapper(UserDAO.class);
		List<User> list = udao.selectAllUser();
		for(User u:list){
			System.out.println(u.getName()+" "+u.getPassword());
		}
		session.close();
	}
	
	@Test
	public void insertUser(){
		User user = new User();
		user.setName("kitty"); user.setPassword("hello");
		SqlSession session = sqlSessionFactory.openSession();
		UserDAO udao = session.getMapper(UserDAO.class);
		udao.insertUser(user);
		session.commit();
		session.close();
	}
	
	@Test
	public void updateUser(){
		User user = new User();
		user.setId(1); user.setName("doris"); user.setPassword("123");
		SqlSession session = sqlSessionFactory.openSession();
		UserDAO udao = session.getMapper(UserDAO.class);
		udao.updateUser(user);
		session.commit();
		session.close();
	}
	
	@Test
	public void deleteUser(){
		SqlSession session = sqlSessionFactory.openSession();
		UserDAO udao = session.getMapper(UserDAO.class);
		udao.deleteUser(2);
		session.commit();
		session.close();
	}
	
	
}
