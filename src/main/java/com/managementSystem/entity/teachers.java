package com.managementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class teachers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String sirName;
	private String address;
	private Double phoneNo;
	private String fatherName;
	private String hoc;
	private String gmailId;
	private String mainSubject;
	private String imageUrl;
	private String dob;
	
	private String userName;
	private String password;

}
