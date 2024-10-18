package com.managementSystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.managementSystem.dto.errorDto;
import com.managementSystem.dto.loginDto;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teacherattendence;
import com.managementSystem.entity.teachers;
import com.managementSystem.service.teacher.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeachersController {

	@Autowired
	private TeacherService teacherservice;

	private Integer standard;
	private teachers teacher;

	@Value("${project.image}")
	private String path;

	@PostMapping("/login")
	public String login(Model model) {
		loginDto login = new loginDto("teacherinfo");
		model.addAttribute("loginAction", login);
		return "login";
	}

	@PostMapping("/teacherinfo")
	public String studentcon(@RequestParam("userId") String userid, @RequestParam("password") String password,
			Model model) {
		teacher = teacherservice.teacher(userid, password);
		if (Objects.nonNull(teacher)) {
				
			model.addAttribute("teacher", teacher);

			return "teacher";

		} else {
			errorDto error = new errorDto("/home", "Ente The vailed Username And Password", "Go to hame page");

			model.addAttribute("error", error);

			return "eror";
		}
	}

	@PostMapping("/attendence")
	public String attendence() {

		return null;
	}

	@GetMapping("back")
	public String back(Model model) {
		model.addAttribute("teacher", teacher);
		return "teacher";
	}

	@GetMapping("/viewstudent/{id}")
	public String viewStudent(@PathVariable("id") Integer standard, Model model) {
		this.standard = standard;
		System.out.println(standard);
		List<students> studentList = teacherservice.getAllStudents(standard);
		if (studentList.isEmpty()) {
			model.addAttribute("teacher", teacher);

			return "teacher";
		} else {
			model.addAttribute("studentList", studentList);
			return "viewStudentByTeacher";
		}
	}

	@GetMapping("/student/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		students s = teacherservice.studentEdit(id);
		model.addAttribute("student", s);
		return "updateStudentByTeacher";

	}

	@PostMapping("saveeditstudent/{id}")
	public String seveStudent(@PathVariable Long id, @ModelAttribute("studen") students student, Model model)
			throws IOException {
		teacherservice.editstudent(id, student);

		model.addAttribute("studentList", teacherservice.getAllStudents(standard));
		return "viewStudentByTeacher";
	}

	@GetMapping("passchange/{id}")
	public String changePassword(@PathVariable Long id, Model model) {
		teachers tech = teacherservice.getByIdt(id);
		model.addAttribute("teacher", tech);
		return "changePassOfTeacher";
	}

	@PostMapping("passchange/{id}")
	public String passwordChange(@PathVariable Long id, @RequestParam("oldpass") String oldPass,
			@RequestParam("newpass") String newPass, @RequestParam("renewpass") String reNewPass) {

		teacherservice.changepass(id, oldPass, newPass, reNewPass);

		return "redirect:/home";
	}

}
