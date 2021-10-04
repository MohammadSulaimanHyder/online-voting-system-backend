package com.app.votingsystem.dto;

public class AuthenticationResponse {
	
	String name;
	String voterId;
	String jwtToken;
	
	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String name, String voterId, String jwtToken) {
		super();
		this.name = name;
		this.voterId = voterId;
		this.jwtToken = jwtToken;
	}
	
	public String getName() {
		return name;
	}
	public String getVoterId() {
		return voterId;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
