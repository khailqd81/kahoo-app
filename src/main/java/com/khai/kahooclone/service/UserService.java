package com.khai.kahooclone.service;

import java.util.List;

import com.khai.kahooclone.entity.RegisterUser;
import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;

public interface UserService {
	
	public List<Role> getListRole();
	public List<User> getListUser();
	public Role findRoleByName(String name);
	public User findUserByUsername(String username);
	public void save(RegisterUser user);
	
}
