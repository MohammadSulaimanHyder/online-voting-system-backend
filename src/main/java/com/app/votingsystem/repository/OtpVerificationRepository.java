package com.app.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.votingsystem.models.OtpVerification;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
	
	public Optional<OtpVerification> findByOtpAndVoterId(String otp, String voterId);

}
