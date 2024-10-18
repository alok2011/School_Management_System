package com.managementSystem.service.teacher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teachers;
import com.managementSystem.repository.StudentRepository;
import com.managementSystem.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepo;
	@Autowired
	private StudentRepository studentRepo;
	private teachers teacher;

//	private students student =new students(),stu;
	public teachers teacher(String username, String password) {

		 teacher = teacherRepo.findByUserNameAndPassword(username, password);
		System.out.println(teacher);
		
		return teacher;

	}
	
	public List<students> getAllStudents(Integer standard) 
	{
		List<students> list = studentRepo.findByStandard(standard);
		
		return list;
	}

	
	
	public students studentEdit(Long id) {
		students student= studentRepo.findById(id).get();
		System.out.println(student);
		return student;
			}
	
	
	public void editstudent(Long id,students student) {
		students existingStudent = studentRepo.findById(id).get();
		existingStudent.setAddress(student.getAddress());
		existingStudent.setDob(student.getDob());
		existingStudent.setFatherName(student.getFatherName());
		existingStudent.setGmailId(student.getGmailId());
		existingStudent.setPhoneNo(student.getPhoneNo());
		studentRepo.save(existingStudent);
	}
	
	public teachers changepass(Long id,String oldpass,String newpass,String renewpass) {
		teachers teach =teacherRepo.findById(id).get();
		System.out.println(teach);
		System.out.println(oldpass);
		System.out.println(newpass);
		System.out.println(renewpass);
		String pass = teach.getPassword();
		System.out.println(pass);
		
		if(oldpass.equals(pass)) {
			if(newpass.equals(renewpass))
			teach.setPassword(renewpass);
			
			String pass1 = teach.getPassword();
			System.out.println(pass1);

			return  teacherRepo.save(teach);
			
		}else {
			return null;
		}
		
	}
	public teachers getByIdt(Long id) {
		
		return teacherRepo.findById(id).get();
		
	}
}
