package com.app.votingsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OtpVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String otp;
	private String voterId;
	
	public OtpVerification() {
		super();
	}
	public OtpVerification(String otp, String voterId) {
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
