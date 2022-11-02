package com.khai.kahooclone.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUser {

	@NotNull(message = "Username is required")
	@Size(min = 5, message = "username length greater than 5")
	private String username;

	@NotNull(message = "Password is required")
	@Size(min = 5, message = "password length greater than 5")
	private String password;

	@NotNull(message = "ConfirmPassword is required")
	@Size(min = 5, message = "password length greater than 5")
	private String confirmPassword;

	private String firstName;

	private String lastName;

	@Email
	private String email;

	private List<String> roles;

	private List<Role> roleInDb;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Role> getRoleInDb() {
		return roleInDb;
	}

	public void setRoleInDb(List<Role> roleInDb) {
		this.roleInDb = roleInDb;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", roles=" + roles + "]";
	}

}
