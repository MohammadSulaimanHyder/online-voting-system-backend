package com.app.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.votingsystem.models.PoliticalParty;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
	
	public Optional<PoliticalParty> getByPartyName(String partyName);

}
