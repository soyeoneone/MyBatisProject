package com.lsp.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import com.lsp.dao.NoteDAO;
import com.lsp.dao.UserDAO;
import com.lsp.entity.Note;

public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory sqlSessionFactory;
	
	static{
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		String path="note_list.jsp";
		if("queryAll".equals(status)){
			SqlSession session = sqlSessionFactory.openSession();
			NoteDAO ndao = session.getMapper(NoteDAO.class);
			UserDAO udao = session.getMapper(UserDAO.class);
			request.setAttribute("notes", ndao.selectAllNotes(new Note()));
			request.setAttribute("users", udao.selectAllUser());
		}
		if("queryByUserid".equals(status)){
			String uid = request.getParameter("id");
			SqlSession session = sqlSessionFactory.openSession();
			NoteDAO ndao = session.getMapper(NoteDAO.class);
			UserDAO udao = session.getMapper(UserDAO.class);
			request.setAttribute("users", udao.selectAllUser());
			request.setAttribute("notes", ndao.selectNoteByUserid(Integer.parseInt(uid)));
		}
		if("queryByNoteid".equals(status)){
			String nid = request.getParameter("id");
			SqlSession session = sqlSessionFactory.openSession();
			NoteDAO ndao = session.getMapper(NoteDAO.class);
			request.setAttribute("note", ndao.selectByNoteid(Integer.parseInt(nid)));
			path="note_content.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
