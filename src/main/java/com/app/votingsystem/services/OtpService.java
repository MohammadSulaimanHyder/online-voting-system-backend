package com.app.votingsystem.services;

import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.votingsystem.dto.NotificationEmail;
import com.app.votingsystem.dto.ValidateOtp;
import com.app.votingsystem.models.OtpVerification;
import com.app.votingsystem.models.Voter;
import com.app.votingsystem.repository.OtpVerificationRepository;

@Service
public class OtpService {
	
	@Autowired
	OtpVerificationRepository otpVerificationRepository;
	@Autowired
	MailService mailService;
	
	public String generateOtp(String voterId) {
		String otp = new Random().ints(4, 0, 10).mapToObj(Integer::toString)
			    .reduce((a, b) -> a + b).get();
		
		OtpVerification otpVerification = new OtpVerification(otp, voterId);
		
		otpVerificationRepository.save(otpVerification);
		
		return otp;
	}
	
	public Boolean validateOtp(ValidateOtp validateOtp) {
		Optional<OtpVerification> otpVerfication = otpVerificationRepository
				.findByOtpAndVoterId(validateOtp.getOtp(), validateOtp.getVoterId());
		
		if(otpVerfication.isPresent()) {
			deleteOtpFromDb(otpVerfication.get());
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public void deleteOtpFromDb(OtpVerification otpVerification) {
		otpVerificationRepository.delete(otpVerification);
	}	
	
	public void sendOtpToEmail(String otp, Voter voter) {
		
		String body = "<html><body>"
				+ "<h1>Your One time password is: " + otp + "</h1>"
				+ "<p>Please do not share your OTP with anyone.</p>"
				+ "</body></html>";
		
		NotificationEmail notificationEmail = new NotificationEmail("Your OTP", voter.getEmail(), body);
		
		mailService.sendMail(notificationEmail);
	}

}
