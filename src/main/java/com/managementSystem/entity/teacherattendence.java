package com.managementSystem.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Data
public class teacherattendence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private LocalDate onlineDate;
	private LocalTime onlineTime;  
	private LocalDate offlineDate;
	private LocalTime offlineTime;

	
	
	public teacherattendence( String status, LocalDate onlineDate, LocalTime onlineTime, LocalDate offlineDate,
			LocalTime offlineTime) {
	
		this.status = status;
		this.onlineDate = onlineDate;
		this.onlineTime = onlineTime;
		this.offlineDate = offlineDate;
		this.offlineTime = offlineTime;
	}

	public teacherattendence() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
