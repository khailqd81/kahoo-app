package com.khai.kahooclone.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.khai.kahooclone.entity.RegisterUser;
import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;
import com.khai.kahooclone.jsonresponse.SuccessResponse;
import com.khai.kahooclone.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUser user,
			BindingResult bindingResult) throws Exception {
		// validation
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			List<String> errorMessage = new ArrayList<>();
			for (FieldError error : errors) {
				errorMessage.add(error.getDefaultMessage());
				System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
			}
			throw new Exception("Validation failed: "+ errorMessage);
		}
		
		// Check exists username
		User theUser = userService.findUserByUsername(user.getUsername());
		if (theUser != null) {
			throw new Exception("Username has existed");
		}
		
		// Check exists roles
		List<Role> listRole = new ArrayList<>();
		for (String role : user.getRoles()) {
			Role theRole = userService.findRoleByName(role);
			if (theRole == null) {
				throw new EntityNotFoundException("Role not found");
			}
			listRole.add(theRole);
		}
		
		user.setRoleInDb(listRole);
		userService.save(user);
		 
//		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
//		return ResponseEntity.created(uri).body("Register Success");
		return new ResponseEntity<>(
				new SuccessResponse(
						HttpStatus.CREATED.value(), "Register Success", System.currentTimeMillis()),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<SuccessResponse> authenicateUser(@RequestBody User user) throws Exception{
		if (user.getUsername().trim().length() <=0 || user.getPassword().trim().length() <=0) {
			throw new Exception("validation failed");
		}
		User userInDb = userService.findUserByUsername(user.getUsername());
		if (userInDb == null) {
			throw new Exception("username not found");
		}
		if (!passwordEncoder.matches(user.getPassword(), userInDb.getPassword())) {
			throw new Exception("password not match");
		}
		return new ResponseEntity<>(
				new SuccessResponse(
						HttpStatus.ACCEPTED.value(), "Login Success", System.currentTimeMillis()),
				HttpStatus.ACCEPTED);
	}
}
