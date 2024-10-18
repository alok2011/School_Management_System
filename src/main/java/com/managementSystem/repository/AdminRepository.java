package com.managementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managementSystem.entity.admin;
import com.managementSystem.entity.students;

public interface AdminRepository extends JpaRepository<admin , Long>{
 
	admin findByUserNameAndPassword(String username,String password);
}
