package com.app.votingsystem.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.votingsystem.dto.CastVote;
import com.app.votingsystem.exception.VotingSystemException;
import com.app.votingsystem.models.ElectoralCandidate;
import com.app.votingsystem.models.Vote;
import com.app.votingsystem.models.Voter;
import com.app.votingsystem.repository.ElectoralCandidateRepository;
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
	
	public List<ElectoralCandidate> getAllElectoralCandidates() {
		return electoralCandidateRepository.findAll();
	}
	
	public Boolean hasVotedBefore(String voterId) {
		Voter voter = voterRepository.findVoterByVoterId(voterId)
				.orElseThrow(() -> new VotingSystemException("Voter with voterId: " + voterId + " not found."));
		
		Optional<Vote> vote = voteRepository.findByVoter_VoterId(voterId);
		
		return vote.isPresent();
	}
	
	@Transactional
	public String castVote(CastVote castVote) {
		Voter voter = voterRepository.findVoterByVoterId(castVote.getVoterId())
				.orElseThrow(() -> new VotingSystemException("Voter with voterId: " + castVote.getVoterId() + " not found."));
		
		ElectoralCandidate candidate = electoralCandidateRepository.findElectoralCandidateBycandidateId(castVote.getVotedForCandidateId())
				.orElseThrow(() -> new VotingSystemException("Canditate with id: " + castVote.getVotedForCandidateId() + " not found."));
		
		Vote vote = new Vote(true, new Date().toString(), voter, candidate);
		voteRepository.save(vote);
		
		candidate.setVoteCount(1);
		electoralCandidateRepository.save(candidate);
		
		return "Succsess";
	}

}
