package com.app.votingsystem.dto;

public class CastVote {
	
	private String voterId;
	private String votedForCandidateId;
	
	public CastVote() {
		super();
	}
	public CastVote(String voterId, String votedForCandidateId) {
		super();
		this.voterId = voterId;
		this.votedForCandidateId = votedForCandidateId;
	}
	public String getVoterId() {
		return voterId;
	}
	public String getVotedForCandidateId() {
		return votedForCandidateId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public void setVotedForCandidateId(String votedForCandidateId) {
		this.votedForCandidateId = votedForCandidateId;
	}
}
