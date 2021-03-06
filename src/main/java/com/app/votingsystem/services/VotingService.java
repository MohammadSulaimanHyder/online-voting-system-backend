package com.app.votingsystem.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.votingsystem.dto.CastVote;
import com.app.votingsystem.dto.ValidateOtp;
import com.app.votingsystem.exception.VotingSystemException;
import com.app.votingsystem.models.ElectoralCandidate;
import com.app.votingsystem.models.PoliticalParty;
import com.app.votingsystem.models.Vote;
import com.app.votingsystem.models.Voter;
import com.app.votingsystem.repository.ElectoralCandidateRepository;
import com.app.votingsystem.repository.PoliticalPartyRepository;
import com.app.votingsystem.repository.VoteRepository;
import com.app.votingsystem.repository.VoterRepository;

@Service
public class VotingService {
	
	@Autowired
	private VoterRepository voterRepository;
	@Autowired
	private ElectoralCandidateRepository electoralCandidateRepository;
	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private OtpService otpService;
	@Autowired
	private PoliticalPartyRepository politicalPartyRepository;
	
	public Boolean validateOtpProvidedByVoter(ValidateOtp validateOtp) {
		return otpService.validateOtp(validateOtp);
	}
	
	public void generateOtpForVoter(String voterId) {
		Voter voter = voterRepository.findVoterByVoterId(voterId)
				.orElseThrow(() -> new VotingSystemException("Voter with voterId: " + voterId + " not found."));
		
		String otp = otpService.generateOtp(voterId);
		
		otpService.sendOtpToEmail(otp, voter);
	}
	
	public List<PoliticalParty> getAllPartyDetials() {
		
		return politicalPartyRepository.findAll();
	}
	
	public List<ElectoralCandidate> getAllElectoralCandidates() {
		return electoralCandidateRepository.findAll();
	}
	
	public Boolean hasVotedBefore(String voterId) {
		Voter voter = voterRepository.findVoterByVoterId(voterId)
				.orElseThrow(() -> new VotingSystemException("Voter with voterId: " + voterId + " not found."));
		
		Optional<Vote> vote = voteRepository.findVoteByVoter(voter);
		
		return vote.isPresent();
	}
	
	@Transactional
	public String castVote(CastVote castVote) {
		Voter voter = voterRepository.findVoterByVoterId(castVote.getVoterId())
				.orElseThrow(() -> new VotingSystemException("Voter with voterId: " + castVote.getVoterId() + " not found."));
		
		ElectoralCandidate candidate = electoralCandidateRepository.findElectoralCandidateBycandidateId(castVote.getVotedForCandidateId())
				.orElseThrow(() -> new VotingSystemException("Canditate with id: " + castVote.getVotedForCandidateId() + " not found."));
		
		Vote vote = new Vote(true, new Date().toString(), voter, candidate);
		
		candidate.setVoteCount(1);
		candidate.setVote(vote);
		electoralCandidateRepository.save(candidate);
		
		voteRepository.save(vote);
		
		PoliticalParty politicalParty = politicalPartyRepository.getByPartyName(candidate.getPartyName())
				.orElseThrow(() -> new VotingSystemException("Party with name: " + candidate.getPartyName() + " not found."));
		
		int count = politicalParty.getCurrentVoteCount();
		count += 1;
		politicalParty.setCurrentVoteCount(count);
		
		politicalPartyRepository.save(politicalParty);
		
		return "{\"status\": \"Success\" }";
	}

}
