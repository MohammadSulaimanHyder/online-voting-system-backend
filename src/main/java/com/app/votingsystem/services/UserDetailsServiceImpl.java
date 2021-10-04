package com.app.votingsystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.votingsystem.models.UserDetialsImpl;
import com.app.votingsystem.models.Voter;
import com.app.votingsystem.repository.VoterRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private VoterRepository voterRepository;

	@Override
	public UserDetails loadUserByUsername(String voterId) throws UsernameNotFoundException {
		
		Optional<Voter> voterOptional = voterRepository.findVoterByVoterId(voterId);
		Voter voter = voterOptional.orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with voterId : " + voterId));
		
		return new UserDetialsImpl(voter, "USER");
	}

}
