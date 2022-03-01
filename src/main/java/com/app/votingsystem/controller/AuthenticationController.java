package com.app.votingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.votingsystem.dto.AuthenticationResponse;
import com.app.votingsystem.dto.LoginRequest;
import com.app.votingsystem.dto.RegisterRequest;
import com.app.votingsystem.services.AuthenticationServices;

@RestController
@RequestMapping("/app/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationServices authenticationServices;
	
	@PostMapping("/voterlogin")
	public ResponseEntity<AuthenticationResponse> voterLogin(@RequestBody LoginRequest loginRequest) {
		
		AuthenticationResponse authenticationResponse = authenticationServices.voterLogin(loginRequest);
		return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> voterSignUp(@RequestBody RegisterRequest registerRequest) {
		String response = authenticationServices.voterSignUp(registerRequest);
		
		if(response.contains("Success")) {
			return new ResponseEntity<String>("User Registration Succesful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authenticationServices.verfyAccount(token);
		return new ResponseEntity<String>("Account Activated Succesfully", HttpStatus.OK);
	}

}
