package com.managementSystem.service.student;

import com.managementSystem.entity.students;

public interface StudentsService {

	
	public students studentre(String username,String password);
	public students getByIdt(Long id);
	public students changepass(Long id,String oldpass,String newpass,String renewpass);
	
}
