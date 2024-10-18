package com.managementSystem.dto;

import lombok.Data;

@Data
public class errorDto {
	private String url;
	private String msg;
	private String buttonMsg;
	
	public errorDto(String url, String msg, String buttonMsg) {
		
		this.url = url;
		this.msg = msg;
		this.buttonMsg = buttonMsg;
	}
	
}
