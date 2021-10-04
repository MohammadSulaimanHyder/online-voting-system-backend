package com.app.votingsystem.dto;

public class RegisterRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String voterId;
	
	
	public RegisterRequest() {
		super();
	}
	public RegisterRequest(String firstName, String lastName, String email, String phoneNumber, String password,
			String voterId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.voterId = voterId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
}
