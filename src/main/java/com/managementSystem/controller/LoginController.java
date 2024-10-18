package com.managementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.managementSystem.entity.admin;
import com.managementSystem.repository.AdminRepository;

//@org.springframework.stereotype.Controller

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private AdminRepository adminRepo;
	
	@GetMapping("home")
	public String Home() {
		System.out.println("LoginController.Home()");
		return "home";
	}

	@GetMapping("addadmin")
	public String addadmin() {
		
		
		return "adminform";
	}
	
	@PostMapping("redirect")
	public String adminadd(@ModelAttribute() admin Admin) {
		System.out.println("LoginController.adminadd()");
		admin ad =adminRepo.save(Admin);
		System.out.println("LoginController.adminadd()");
		if(ad.equals(Admin)) {
			
			return "home";
		}else{
			return "eror";
		}
			
		
	}

}
