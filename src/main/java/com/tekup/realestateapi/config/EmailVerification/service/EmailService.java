package com.tekup.realestateapi.config.EmailVerification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tekup.realestateapi.config.EmailVerification.EmailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
	
	private final static Logger LOGGER=LoggerFactory.getLogger(EmailSender.class);

	private final JavaMailSender mailSender;

	
	@Override
	@Async
	public void send(String to, String email) {

		try{
			MimeMessage message=mailSender.createMimeMessage();
			MimeMessageHelper helper=
					new MimeMessageHelper(message,"utf-8");
			helper.setText(email,true);
			helper.setTo(to);
			helper.setSubject("Confirm Your Email");
			helper.setFrom("scongresses@gmail.com");
			mailSender.send(message);
	        LOGGER.info("Email sent successfully to: {}", to);

			
		}catch(MessagingException e)
		{
	        LOGGER.error("Failed to send email to {}: {}", to, e.getMessage());

			throw new IllegalStateException("failed to send email");
		}
		
	}
	

}
