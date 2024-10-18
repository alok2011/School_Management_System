package com.managementSystem.service.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.managementSystem.entity.admin;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teachers;

public interface AdminService {
	public admin adminn(String username, String password);
	public students saveStudent(MultipartFile image,students student ,String path)throws IOException ;
	public teachers saveTeacher(MultipartFile image,teachers teacher,String path) throws IOException;
	public List<students> getStudents(Integer standard);
	public List<teachers> getAllTeacher();
	public students studentEdit(Long id);
	public teachers teacherEdit(Long id);
	public void deleteTeacher(Long id);
	public void deleteStudent(Long id);
	public void editTeacher(Long id, teachers teacher);
	public void editstudent(Long id, students student);
}
