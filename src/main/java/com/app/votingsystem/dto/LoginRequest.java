package com.app.votingsystem.dto;

public class LoginRequest {
	
	private String voterId;
	private String password;
	
	public LoginRequest() {
		super();
	}
	
	public LoginRequest(String voterId, String password) {
		super();
		this.voterId = voterId;
		this.password = password;
	}
	
	public String getVoterId() {
		return voterId;
	}
	public String getPassword() {
		return password;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
