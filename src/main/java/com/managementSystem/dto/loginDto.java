package com.managementSystem.dto;

import lombok.Data;

@Data
public class loginDto {
	
private String action;

public loginDto(String action) {

	this.action = action;
}

}
