package com.managementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managementSystem.entity.students;



public interface StudentRepository extends JpaRepository <students , Long> {

	students findByUserNameAndPassword(String username,String password);
	List<students> findByStandard(Integer standard);
	
}
