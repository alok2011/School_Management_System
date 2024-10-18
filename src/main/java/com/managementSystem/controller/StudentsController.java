package com.managementSystem.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.managementSystem.dto.errorDto;
import com.managementSystem.dto.loginDto;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teachers;
import com.managementSystem.service.student.StudentsService;

@Controller
@RequestMapping("/student")
public class StudentsController {

	@Autowired
	private StudentsService studentservice;
	private students student;

	
	@PostMapping("/login")
	public String login(Model model) {
		
		loginDto login = new loginDto("studentinfo");
		
		
		model.addAttribute("loginAction",login);
		return "login";
		}
	
	@PostMapping("/studentinfo")
	public String studentcon(@RequestParam("userId") String userid, @RequestParam("password") String password ,Model model) {
		student=studentservice.studentre(userid,password);
		if(Objects.nonNull(student)) {
         model.addAttribute("stu",student);
		return "student";
		}else {
			errorDto error = new errorDto("/home","Ente The vailed Username And Password","Go to hame page");
			
			model.addAttribute("error",error);
			return"eror";
		}
	}
	
	@GetMapping("passchange/{id}")
	public String changePassword(@PathVariable Long id,Model model) {
		students student =studentservice.getByIdt(id);
		
		model.addAttribute("student", student);
		return "changePassOfStudent";
	}
	
	@PostMapping("passchange/{id}")
	public String passwordChange(@PathVariable Long id,@RequestParam("oldpass") String oldPass,@RequestParam("newpass") String newPass,@RequestParam("renewpass") String reNewPass) {
		
		studentservice.changepass(id,oldPass,newPass,reNewPass);
		
		
		return "redirect:/home";
	}
	@GetMapping("/back")
	public String back(Model model) {
		model.addAttribute("stu",student);
		return "student";
	}
}
