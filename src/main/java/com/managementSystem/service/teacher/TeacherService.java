package com.managementSystem.service.teacher;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.managementSystem.entity.students;
import com.managementSystem.entity.teachers;

public interface TeacherService {

	public teachers teacher(String username, String password);

	public List<students> getAllStudents(Integer standard);
	public void editstudent(Long id,students student) ;
	public students studentEdit(Long id);
	public teachers changepass(Long id,String oldpass,String newpass,String renewpass);
	public teachers getByIdt(Long id);
}
