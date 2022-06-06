package com.pulkit.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.pulkit.flightreservation.email.subject}")
	private String EMAIL_BODY;

	@Value("${com.pulkit.flightreservation.email.body}")
	private String EMAIL_SUBJECT;

	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	public void sendItineary(String toAddress , String filePath) {
		LOGGER.info("Inside sendItineary()");
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itineary" , new File(filePath));
			mailSender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItineary() which is " + e);
		}
		
	}
	
}
