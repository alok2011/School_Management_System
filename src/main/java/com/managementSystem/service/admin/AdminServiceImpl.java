package com.managementSystem.service.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.managementSystem.entity.admin;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teacherattendence;
import com.managementSystem.entity.teachers;
import com.managementSystem.repository.AdminRepository;
import com.managementSystem.repository.StudentRepository;
import com.managementSystem.repository.TeacherRepository;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private TeacherRepository teacherRepo;

	private students student1, student2;
	private teachers teacher1, teacher2;

	public admin adminn(String username, String password) {

		admin manager = adminRepo.findByUserNameAndPassword(username, password);
		System.out.println(manager);
		return manager;

	}

	public students saveStudent(MultipartFile image, students student, String path) throws IOException {

		String name = image.getOriginalFilename();

//		String p =""

		String randomId = UUID.randomUUID().toString();
		String randFile = randomId.concat(name.substring(name.lastIndexOf(".")));
		String filepath = path + randFile;

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		student.setImageUrl(randFile);

		student1 = studentRepo.save(student);

		Files.copy(image.getInputStream(), Paths.get(filepath));
		return student1;
	}

	@Override
	public teachers saveTeacher(MultipartFile image, teachers teacher, String path) throws IOException {

		String name = image.getOriginalFilename();

//		String p =""

		String randomId = UUID.randomUUID().toString();
		String randFile = randomId.concat(name.substring(name.lastIndexOf(".")));
		String filepath = path + randFile;

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		teacher.setImageUrl(randFile);

		teacher1 = teacherRepo.save(teacher);

		Files.copy(image.getInputStream(), Paths.get(filepath));

		return teacher1;
	}

	public List<students> getStudents(Integer standard) {
		List<students> list = studentRepo.findByStandard(standard);
		return list;
	}

	public List<teachers> getAllTeacher() {

		return teacherRepo.findAll();
	}

	public students studentEdit(Long id) {
		student2 = studentRepo.findById(id).get();
		System.out.println(student2);
		return student2;
	}

	public void deleteStudent(Long id) {
		studentRepo.deleteById(id);
	}

	public teachers teacherEdit(Long id) {
		teacher2 = teacherRepo.findById(id).get();
		return teacher2;
	}

	public void deleteTeacher(Long id) {
		teacherRepo.deleteById(id);
	}

	public void editstudent(Long id, students student) {
		students existingStudent = studentRepo.findById(id).get();
		existingStudent.setName(student.getName());
		existingStudent.setSirName(student.getSirName());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setDob(student.getDob());
		existingStudent.setFatherName(student.getFatherName());
		existingStudent.setGmailId(student.getGmailId());
		existingStudent.setPhoneNo(student.getPhoneNo());
		existingStudent.setStandard(student.getStandard());
		existingStudent.setUserName(student.getUserName());

		studentRepo.save(existingStudent);

	}

	public void editTeacher(Long id, teachers teacher) {
		teachers existingTeacher = teacherRepo.findById(id).get();
		existingTeacher.setName(teacher.getName());
		existingTeacher.setSirName(teacher.getSirName());
		existingTeacher.setAddress(teacher.getAddress());
		existingTeacher.setDob(teacher.getDob());
		existingTeacher.setFatherName(teacher.getFatherName());
		existingTeacher.setGmailId(teacher.getGmailId());
		existingTeacher.setPhoneNo(teacher.getPhoneNo());
		existingTeacher.setHoc(teacher.getHoc());
		existingTeacher.setUserName(teacher.getUserName());

		teacherRepo.save(existingTeacher);

	}
}
