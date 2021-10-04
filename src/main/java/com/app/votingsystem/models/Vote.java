package com.app.votingsystem.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean hasVoted = false;
	private String date;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voterId", referencedColumnName = "voterId")
	private Voter voter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidateId", referencedColumnName = "candidateId")
	private ElectoralCandidate electoralCandidate;
	

	public Vote() {
		super();
	}

	public Vote(Boolean hasVoted, String date, Voter voter, ElectoralCandidate electoralCandidate) {
		super();
		this.hasVoted = hasVoted;
		this.date = date;
		this.voter = voter;
		this.electoralCandidate = electoralCandidate;
	}

	public Long getId() {
		return id;
	}

	public Boolean getHasVoted() {
		return hasVoted;
	}

	public String getDate() {
		return date;
	}

	public Voter getVoter() {
		return voter;
	}

	public ElectoralCandidate getElectoralCandidate() {
		return electoralCandidate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHasVoted(Boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public void setElectoralCandidate(ElectoralCandidate electoralCandidate) {
		this.electoralCandidate = electoralCandidate;
	}
	
}
