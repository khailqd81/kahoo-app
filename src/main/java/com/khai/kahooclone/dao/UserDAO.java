package com.khai.kahooclone.dao;

import java.util.List;

import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;

public interface UserDAO {
	public User findUserByUsername(String username);
	public void save(User user);
	public List<Role> getListRole();
	public Role findRoleByName(String name);
	public List<User> getListUser();
}
