package com.app.votingsystem.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.votingsystem.dto.AuthenticationResponse;
import com.app.votingsystem.dto.LoginRequest;
import com.app.votingsystem.dto.NotificationEmail;
import com.app.votingsystem.dto.RegisterRequest;
import com.app.votingsystem.exception.VotingSystemException;
import com.app.votingsystem.models.UserDetialsImpl;
import com.app.votingsystem.models.VerificationToken;
import com.app.votingsystem.models.Voter;
import com.app.votingsystem.repository.VerificationTokenRepository;
import com.app.votingsystem.repository.VoterRepository;


@Service
public class AuthenticationServices {
	
	@Autowired
	private VoterRepository voterRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private MailService mailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtilService jwtUtilService;
	
	public AuthenticationResponse voterLogin(@RequestBody LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getVoterId(), 
				loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		UserDetialsImpl voterDetails = (UserDetialsImpl) authenticate.getPrincipal();
		
		String jwtToken = jwtUtilService.generateToken(voterDetails);
		
		return new AuthenticationResponse(voterDetails.getUsername(), voterDetails.getVoterId(), jwtToken);
	}
	
	
	public void verfyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
			verificationToken.orElseThrow(() -> new VotingSystemException("Invalid Token"));
			
		fetchUserAndEnable(verificationToken.get());
	}
	
	@Transactional
	public void fetchUserAndEnable(VerificationToken verificationToken) {
		
		String voterId = verificationToken.getVoter().getVoterId();
		
		Voter voter = voterRepository.findVoterByVoterId(voterId)
					.orElseThrow(() -> new VotingSystemException("User not found - " + verificationToken.getVoter().getFirstName()));
		
		voter.setEnabled(true);
		voterRepository.save(voter);
	}
	
	@Transactional
	public String voterSignUp(@RequestBody RegisterRequest registerRequest) {
		
		Voter voter = new Voter();
		voter.setFirstName(registerRequest.getFirstName());
		voter.setLastName(registerRequest.getLastName());
		voter.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
		voter.setEmail(registerRequest.getEmail());
		voter.setPhoneNumber(registerRequest.getPhoneNumber());
		voter.setVoterId(registerRequest.getVoterId());
		
		if(voterRepository.findVoterByVoterId(voter.getVoterId()).isPresent()) {
			return "User " + voter.getFirstName() + " already registerd. If you haven't registered before"
				 + "please contact admin.";
		} else {
			voterRepository.save(voter);
		}
		
		String token = generateVerificationToken(voter);
		
		NotificationEmail notificationEmail = new NotificationEmail("Please activate your account",
				voter.getEmail(), 
				"Thank you for signing up, Please click on the below link to signup: "
				+ "http://localhost:8080/app/auth/accountVerification/" + token);
		
		mailService.sendMail(notificationEmail);
		
		return "Success";
	}
	
	public String generateVerificationToken(Voter voter) {
		
		String token = UUID.randomUUID().toString();
		
		VerificationToken verificationToken = new VerificationToken();
		
		verificationToken.setToken(token);
		verificationToken.setVoter(voter);
		
		verificationTokenRepository.save(verificationToken);
		
		return token;
	}

}
