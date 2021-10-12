package com.app.votingsystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ElectoralCandidate implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String candidateId;
	private String firstName;
	private String lastName;
	private String partyName;
	private String image;
	
	private Integer voteCount = 0;
	
	@JsonIgnore
	@OneToMany(mappedBy = "electoralCandidate")
	private List<Vote> vote = new ArrayList<Vote>();
	

	public ElectoralCandidate() {
		super();
	}

	public ElectoralCandidate(String firstName, String lastName, String partyName, String image) {
		super();
		this.candidateId = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.partyName = partyName;
		this.image = image;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount += voteCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public List<Vote> getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote.add(vote);
	}

}
