package com.app.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.votingsystem.models.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
	
	public Optional<Voter> findVoterByVoterId(String voterId);

}
