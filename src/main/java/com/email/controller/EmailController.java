package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;

@CrossOrigin
@RestController
@RequestMapping(path = "/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping(path = "/welcome")
	public String welcome() {
		return "Hello this is my email api...";
	}

	// api to send email

	@PostMapping(path = "/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
		Boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if (result) {
			return ResponseEntity.ok(new EmailResponse("Email Sent Successfully..."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email Not send..."));
		}
	}
}
