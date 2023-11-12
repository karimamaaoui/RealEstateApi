package com.tekup.realestateapi.config.EmailVerification;

public interface EmailSender {
	void send(String to,String email);
}
