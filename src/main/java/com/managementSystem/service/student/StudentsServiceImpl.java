package com.managementSystem.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementSystem.entity.admin;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teachers;
import com.managementSystem.repository.AdminRepository;
import com.managementSystem.repository.StudentRepository;
import com.managementSystem.repository.TeacherRepository;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private StudentRepository studentRepo;

	/* private students student; */

	public students studentre(String username, String password) {
		students studentinfo = studentRepo.findByUserNameAndPassword(username, password);
		System.out.println(studentinfo);
		return studentinfo;
	}

	public students changepass(Long id, String oldpass, String newpass, String renewpass) {
		students student = studentRepo.findById(id).get();

		String pass = student.getPassword();

		if (oldpass.equals(pass)) {
			if (newpass.equals(renewpass))
				student.setPassword(renewpass);
			return studentRepo.save(student);

		} else {
			return null;
		}

	}

	public students getByIdt(Long id) {

		return studentRepo.findById(id).get();

	}

}
