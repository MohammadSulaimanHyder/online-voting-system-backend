package com.app.votingsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PoliticalParty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String partyName;
	private String partyLogo;
	private Integer currentVoteCount;
	
	public PoliticalParty() {
		super();
	}
	public PoliticalParty(String partyName, String partyLogo, Integer currentVoteCount) {
		super();
		this.partyName = partyName;
		this.partyLogo = partyLogo;
		this.currentVoteCount = currentVoteCount;
	}
	public String getPartyName() {
		return partyName;
	}
	public String getPartyLogo() {
		return partyLogo;
	}
	public Integer getCurrentVoteCount() {
		return currentVoteCount;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public void setPartyLogo(String partyLogo) {
		this.partyLogo = partyLogo;
	}
	public void setCurrentVoteCount(Integer currentVoteCount) {
		this.currentVoteCount = currentVoteCount;
	}
}
