package com.notes.saver.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notes.saver.core.dto.ApiResponse;
import com.notes.saver.core.entities.Auth;
import com.notes.saver.core.services.AuthService;

@RestController
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping(path = "/auth/signup")
	public ResponseEntity<ApiResponse> signupUser(@RequestBody Auth auth) {
		ResponseEntity<ApiResponse> saveUser = service.signUpUser(auth);
		return saveUser;
	}

	@PostMapping(path = "/auth/login")
	public ResponseEntity<ApiResponse> loginUser(@RequestBody Auth auth) {
		ResponseEntity<ApiResponse> loginUser = service.loginUser(auth);
		return loginUser;
	}
}
