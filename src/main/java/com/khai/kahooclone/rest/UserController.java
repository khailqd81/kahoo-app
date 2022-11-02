package com.khai.kahooclone.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;
import com.khai.kahooclone.repo.UserRepository;
import com.khai.kahooclone.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
//	
//	@Autowired
//	private UserService userService;
//	
//	@GetMapping("/roles")
//	public List<Role> getListRole() {
//		return userService.getListRole();
//	}
//	
//	@GetMapping("/users")
//	public List<User> getListUser() {
//		return userService.getListUser();
//	}
	@Autowired
	private UserService userService;

//	@GetMapping("/roles")
//	public List<Role> getListRole() {
//		return userRespository.getListRole();
//	}

	@GetMapping("/users")
	public List<User> getListUser() {
		return userService.getListUser();
	}

//	@GetMapping("/users/search")
//	public List<User> searchUser(@RequestParam Map<String, String> allParams) {
//		System.out.println("Search user");
//		for (Map.Entry<String, String> entry : allParams.entrySet()) {
//			String key = entry.getKey();
//			String val = entry.getValue();
//			System.out.println("==>> " + key + ": " + val);
//		}
//		//List<User> theUser = userRespository.findByUsername(allParams.get("username"));
//		List<String> nameList = new ArrayList<String>();
//		nameList.add("teacher");
//		nameList.add("19120250");
//		Role theRole = new Role("Student");
//		theRole.setRoleId(3);
//		List<User> theUserRole = userService.findByRoles(theRole);
//		System.out.println("Find by role: ");
//		for (User user : theUserRole) {
//			System.out.println(user);
//		}
//		System.out.println("End by role: ");
//		List<User> theUser = userService.findByUsernameIn(nameList);
//		return theUser;
//	}
}
