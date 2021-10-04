package com.app.votingsystem.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "token")
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Voter voter;
	
	private Instant expiryDate;

	public VerificationToken() {
		super();
	}

	public VerificationToken(String token, Voter voter, Instant expiryDate) {
		super();
		this.token = token;
		this.voter = voter;
		this.expiryDate = expiryDate;
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public Voter getVoter() {
		return voter;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
