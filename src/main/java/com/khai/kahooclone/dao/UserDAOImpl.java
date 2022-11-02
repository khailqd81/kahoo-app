package com.khai.kahooclone.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khai.kahooclone.entity.Role;
import com.khai.kahooclone.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findUserByUsername(String username) {
		Query query = entityManager.createQuery("from User where username=:theUsername", User.class);
		query.setParameter("theUsername", username);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	@Override
	public List<User> getListUser() {
		Query query = entityManager.createQuery("from User");
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public void save(User user) {
		User userDb = entityManager.merge(user);
		user.setUserId(userDb.getUserId());
	}

	@Override
	public List<Role> getListRole() {
		Query query = entityManager.createQuery("from Role");
		List<Role> roleList = query.getResultList();
		/*
		 * for (Role role : roleList) { System.out.println(role.getUsers()); }
		 */
		return roleList;
	}

	@Override
	public Role findRoleByName(String name) {
		Query query = entityManager.createQuery("from Role where name=:roleName", Role.class);
		query.setParameter("roleName", name);
		Role role = null;
		try {
			role = (Role) query.getSingleResult();
		} catch (Exception e) {
			role = null;
		}

		return role;
	}

}
