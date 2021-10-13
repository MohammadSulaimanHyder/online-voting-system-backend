package com.app.votingsystem.dto;

public class ValidateOtp {
	
	private String otp;
	private String voterId;
	
	public ValidateOtp() {
		super();
	}
	public ValidateOtp(String otp, String voterId) {
		super();
		this.otp = otp;
		this.voterId = voterId;
	}
	public String getOtp() {
		return otp;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
}
