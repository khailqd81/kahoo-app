package com.khai.kahooclone.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="role", schema="public")
public class Role {
	
	@Id
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={ CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "user_role",
			joinColumns= @JoinColumn(name="role_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	
	public Role() {
		
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
