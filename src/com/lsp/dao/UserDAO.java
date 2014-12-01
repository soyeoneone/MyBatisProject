package com.lsp.dao;

import java.util.List;

import com.lsp.entity.User;

public interface UserDAO {
	public User selectUser(int id);
	public List<User> selectAllUser();
	public void insertUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	
}
