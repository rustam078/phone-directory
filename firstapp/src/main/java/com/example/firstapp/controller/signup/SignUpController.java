package com.example.firstapp.controller.signup;





import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.firstapp.error.EmailExistsException;
import com.example.firstapp.modell.Login;
import com.example.firstapp.modell.SignUp;
import com.example.firstapp.service.SignUpService;


@RestController
public class SignUpController {

	@Autowired
	private SignUpService signupservice;
	

	@CrossOrigin
	@PostMapping("/register")
	public ResponseEntity<?> userRegistration(@RequestBody SignUp entity) throws  EmailExistsException {
		return   signupservice.userRegistration(entity);
	}
	
	
	@CrossOrigin
	@GetMapping("/employees")
	public ResponseEntity<?> getAllSignupDetails()  {
	
		return   signupservice.getAllSignupDetails();
		
	}
	

	@CrossOrigin
	@DeleteMapping("/deletealll")
	public void deleteAllSignUPDetails() {
		 signupservice.deleteAllSignUPDetails();
	}

	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<String> SigninValidation(@RequestBody Login loginentity) {
		return signupservice.SigninValidation(loginentity);
	}
	
}
