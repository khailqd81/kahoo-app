package com.khai.kahooclone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer >{
	public User findByUsername(String username);
	public List<User> findByRoles(Role role);
	public List<User> findByUsernameIn(List<String> usernames);
	public List<User> findByRolesIn(List<Role> roles);
}
