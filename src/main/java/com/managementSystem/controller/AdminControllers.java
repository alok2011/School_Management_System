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
import org.springframework.web.multipart.MultipartFile;

import com.managementSystem.dto.errorDto;
import com.managementSystem.dto.loginDto;
import com.managementSystem.entity.admin;
import com.managementSystem.entity.students;
import com.managementSystem.entity.teachers;
import com.managementSystem.service.admin.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminControllers {

	@Autowired
	private AdminService adminService;
	private admin Admin;
	private students stu= new students();
	private teachers teach =new teachers();
	
	@Value("${project.image}")
	private String path;
	
	
	
	private Integer standard;

	@PostMapping("/login")
	public String login(Model model) {
		loginDto login = new loginDto("/admin/info");
		model.addAttribute("loginAction",login);
		return "login";
	}

	@PostMapping("/info")
	public String studentcon(@RequestParam("userId") String userid, @RequestParam("password") String password, Model model) {
		Admin = adminService.adminn(userid, password);
		if(Objects.nonNull(Admin)){
			model.addAttribute("admin",Admin);
			
			return "admin";
			
		}else {
			errorDto error =new errorDto("/home","Ente The vailed Username And Password","Go to hame page");
			
			model.addAttribute("error",error);
		
			return "eror";
	}
	}

	@GetMapping("/back")
	public String back(Model model) {
		model.addAttribute("admin",Admin);
		return "admin";
	}
	
	//****************Student*******************
	
	
	
	@GetMapping("addstudent")
	public String addStudent() {
		return "addstudentform";
	}
	
	@PostMapping("redirectstudent")
	public String redirectStudent(
			@RequestParam("imageUrl")MultipartFile image,
			@RequestParam("name") String Name ,
			@RequestParam("sirName") String SirName ,
			@RequestParam("address") String Address ,
			@RequestParam("phoneNo") Double PhoneNo ,
			@RequestParam("fatherName") String FatherName ,
			@RequestParam("dob") String Dob ,
			@RequestParam("standard") Integer Standard ,
			@RequestParam("gmailId") String GmailId ,
			@RequestParam("userName") String UserName ,
			@RequestParam("password") String Password ,Model model) throws IOException {
		
		
		stu.setAddress(Address);
		stu.setDob(Dob);
		stu.setFatherName(FatherName);
		stu.setGmailId(GmailId);
		stu.setName(Name);
		stu.setPassword(Password);
		stu.setPhoneNo(PhoneNo);
		stu.setSirName(SirName);
		stu.setStandard(Standard);
		stu.setUserName(UserName);
		students student=adminService.saveStudent( image, stu,path);
		
		if(student.equals(stu)) {
			model.addAttribute("admin",Admin);
			return "admin";
		}else{
			return "eror";
		}
				
		
		
	}
	
	
	@GetMapping("/viewstudent/{id}")
	public String viewStudent(@PathVariable("id") Integer standard, Model model) {
		this.standard=standard;
		List<students> student = adminService.getStudents(standard);
		System.out.println(standard);
		if(student.isEmpty()) {
			return "eror";
		}else {
		model.addAttribute("studentList", student);
		return "viewStudent";
	}
	}
	
	
	
	@GetMapping("/student/edit/{id}")
	public String editStudent(@PathVariable Long id,Model model) {
		students s = adminService.studentEdit(id);
		model.addAttribute("student",s);
		return "editStudent";
		
	}
	
	@PostMapping("saveeditstudent/{id}")
	public String seveStudent(@PathVariable Long id,@ModelAttribute("studen" )students student
			,Model model) throws IOException {
		   adminService.editstudent(id,student);
		
		 
			model.addAttribute("studentList",adminService.getStudents(standard));
			return "viewStudent";
		
		
	}
	
	@GetMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable Long id,Model model) {
		adminService.deleteStudent(id);
		model.addAttribute("studentList", adminService.getStudents(standard));
		return "viewStudent";
				
	}
	
	// *******************Teacher************************** 
	
	
	@GetMapping("/addteacher")
	public String addTeacher() {
		return "addteacherform";
	}
	
	@PostMapping("redirectteacher")
	public String redirectTeacher(
			@RequestParam("imageUrl")MultipartFile image,
			@RequestParam("name") String Name ,
			@RequestParam("sirName") String SirName ,
			@RequestParam("address") String Address ,
			@RequestParam("phoneNo") Double PhoneNo ,
			@RequestParam("fatherName") String FatherName ,
			@RequestParam("dob") String Dob ,
			@RequestParam("standard")String Standard ,
			@RequestParam("gmailId") String GmailId ,
			@RequestParam("mainSubject") String MainSubject ,
			@RequestParam("userName") String UserName ,
			@RequestParam("password") String Password ,Model model)throws IOException  {
		
		teach.setAddress(Address);
		teach.setDob(Dob);
		teach.setFatherName(FatherName);
		teach.setGmailId(GmailId);
		teach.setName(Name);
		teach.setPassword(Password);
		teach.setPhoneNo(PhoneNo);
		teach.setSirName(SirName);
		teach.setHoc(Standard);
		teach.setMainSubject(MainSubject);
		teach.setUserName(UserName);
		
		teachers tech=adminService.saveTeacher(image, teach,path);
		
		if(tech.equals(teach)) {
			model.addAttribute("admin",Admin);
			return "admin";
		}else{
			return "eror";
		}
		
	}
	

	@GetMapping("viewteacher")
	public String viewteacher( Model model) {
		
		List<teachers> teacherList = adminService.getAllTeacher();
		if(teacherList.isEmpty()) {
			return "eror";
		}else {
		model.addAttribute("teacherList",teacherList);
		return "viewteacher";
	}
	}
	
	@GetMapping("/teacher/edit/{id}")
	public String editTeacher(@PathVariable Long id,Model model) {
		teachers T =adminService.teacherEdit(id);
		model.addAttribute("teacher",T);
		return "editteacher";
		
	}
	
	@PostMapping("/saveeditteacher/{id}")
	public String seveTeacher(@PathVariable Long id ,@ModelAttribute teachers teacher ,Model model) throws IOException {
		 adminService.editTeacher(id,teacher);
			
			model.addAttribute("teacherList",adminService.getAllTeacher());
			return "viewteacher";
		
		
	}
	
	@GetMapping("/teacher/delete/{id}")
	public String deleteTeacher(@PathVariable Long id,Model model) {
		adminService.deleteTeacher(id);
		model.addAttribute("studentList", adminService.getAllTeacher());
		return "viewTeacher";
				
	}

	
}
