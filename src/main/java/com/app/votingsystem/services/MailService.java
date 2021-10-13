package com.app.votingsystem.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.votingsystem.dto.NotificationEmail;
import com.app.votingsystem.exception.VotingSystemException;


@Service
public class MailService {
	
	@Autowired
	private final JavaMailSender mailSender = null;
	
	@Async
	void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("votingsystem@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
        	throw new VotingSystemException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }

}
