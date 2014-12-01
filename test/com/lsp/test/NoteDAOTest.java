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

import com.lsp.dao.NoteDAO;
import com.lsp.entity.Note;

public class NoteDAOTest {
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
	public void queryNotesByUserid(){
		SqlSession session = sqlSessionFactory.openSession();
		NoteDAO ndao = session.getMapper(NoteDAO.class);
		List<Note> notes = ndao.selectNoteByUserid(1);
		System.out.println(notes.size());
		for(Note n:notes){
			System.out.println(n.getTitle()+" "+n.getUser().getName());
		}
		session.close();
	}
}
