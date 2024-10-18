package com.managementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managementSystem.entity.teachers;

public interface TeacherRepository extends  JpaRepository <teachers , Long> {

	teachers findByUserNameAndPassword(String username,String password);
}
