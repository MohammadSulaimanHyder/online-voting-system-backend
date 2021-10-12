package com.app.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.votingsystem.models.Vote;
import com.app.votingsystem.models.Voter;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	//Voter_VoterId tells JPA to look for a Voter with child value VoterId.
	public Optional<Vote> findVoteByVoter(Voter voter);

}
