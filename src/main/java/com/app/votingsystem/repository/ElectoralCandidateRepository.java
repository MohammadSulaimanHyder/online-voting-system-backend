package com.app.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.votingsystem.models.ElectoralCandidate;

@Repository
public interface ElectoralCandidateRepository extends JpaRepository<ElectoralCandidate, Long> {
	
	public Optional<ElectoralCandidate> findElectoralCandidateBycandidateId(String id);

}
