package com.khai.kahooclone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khai.kahooclone.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String roleName);
}
