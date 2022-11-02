package com.khai.kahooclone.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.khai.kahooclone.entity.RegisterUser;
import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;
import com.khai.kahooclone.repo.RoleRepository;
import com.khai.kahooclone.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public List<Role> getListRole() {
		return roleRepository.findAll();
	}

	@Override
	@Transactional
	public User findUserByUsername(String username) {
		User user =userRepository.findByUsername(username);
		return user;
	}

	@Override
	@Transactional
	public void save(RegisterUser user) {
		User saveUser = new User();
		saveUser.setUsername(user.getUsername());
		saveUser.setFirstName(user.getFirstName());
		saveUser.setLastName(user.getLastName());
		saveUser.setEmail(user.getEmail());
		saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
		saveUser.setRoles(user.getRoleInDb());
		userRepository.save(saveUser);
	}

	@Override
	@Transactional
	public Role findRoleByName(String roleName) {
		return roleRepository.findByName(roleName);
	}

	@Override
	@Transactional
	public List<User> getListUser() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		} 
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}

}
