package com.app.votingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.votingsystem.dto.CastVote;
import com.app.votingsystem.dto.ValidateOtp;
import com.app.votingsystem.models.ElectoralCandidate;
import com.app.votingsystem.models.PoliticalParty;
import com.app.votingsystem.services.VotingService;

@Controller
@RequestMapping("/app/voting")
public class VotingController {
	
	@Autowired
	private VotingService votingService;
	
	@PutMapping("/validateotp")
	public ResponseEntity<Boolean> validateOtp(@RequestBody ValidateOtp validateOtp) {
		Boolean response = votingService.validateOtpProvidedByVoter(validateOtp);	
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getotp/{voterId}")
	public ResponseEntity<String> generateOtpForVoter(@PathVariable String voterId) {
		votingService.generateOtpForVoter(voterId);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/getelectoralcandidate")
	public ResponseEntity<List<ElectoralCandidate>> getAllElectoralCandidates() {
		List<ElectoralCandidate> candiateList = votingService.getAllElectoralCandidates();
		
		return new ResponseEntity<List<ElectoralCandidate>>(candiateList, HttpStatus.OK);
	}
	
	@GetMapping("/getallpartydetails")
	public ResponseEntity<List<PoliticalParty>> getAllPartyDetials() {
		
		List<PoliticalParty> partyDetails = votingService.getAllPartyDetials();

		return new ResponseEntity<List<PoliticalParty>>(partyDetails, HttpStatus.OK);
	}
	
	@GetMapping("/hasvotedbefore/{voterId}")
	public ResponseEntity<Boolean> hasVotedBefore(@PathVariable String voterId) {
		
		Boolean hasVoted = votingService.hasVotedBefore(voterId);
		return new ResponseEntity<Boolean>(hasVoted, HttpStatus.OK);
	}
	
	@PostMapping("/vote")
	public ResponseEntity<String> vote(@RequestBody CastVote castVote) {
		
		String response = votingService.castVote(castVote);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
