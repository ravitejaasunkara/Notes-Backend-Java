package com.notes.saver.core.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.notes.saver.core.dto.ApiResponse;
import com.notes.saver.core.dto.LoginResponseDTO;
import com.notes.saver.core.entities.Auth;
import com.notes.saver.core.repositories.AuthRepository;

@Service
public class AuthService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private AuthRepository authRepository;
	
	public ResponseEntity<ApiResponse> signUpUser(@RequestBody Auth auth) {
		Optional<Auth> userExist = authRepository.findById(auth.getId());
		Auth findByEmail = authRepository.findByEmail(auth.getEmail());
		if(userExist.isEmpty()) {
			if(findByEmail != null) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ApiResponse(false,null,"Email already exists."));
			}
			authRepository.save(auth);
			LoginResponseDTO obj = new LoginResponseDTO(auth.getId(),auth.getUsername(),auth.getEmail());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse(true,obj,"User created."));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiResponse(false,null,"Some error has occured."));
	}
	
	public ResponseEntity<ApiResponse> loginUser(@RequestBody Auth auth) {
		List<Auth> findByUsername = authRepository.findByUsername(auth.getUsername());
		Auth user;
		for(int i = 0;i < findByUsername.size();i++) {
			if(findByUsername.get(i).getUsername().toLowerCase().equals(auth.getUsername().toLowerCase())
					&& findByUsername.get(i).getPassword().equals(auth.getPassword())) {
				user = findByUsername.get(i);
				LoginResponseDTO obj = new LoginResponseDTO(user.getId(),user.getUsername(),user.getEmail());
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ApiResponse(true,obj,"Login successful"));
			}
		}
		Auth findByEmail = authRepository.findByEmail(auth.getEmail());
		if(findByEmail.getEmail().toLowerCase().equals(auth.getEmail()) && 
				findByEmail.getPassword().equals(auth.getPassword())) {
			LoginResponseDTO obj = new LoginResponseDTO(findByEmail.getId(),findByEmail.getUsername(),findByEmail.getEmail());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponse(true,obj,"Login successful"));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(false,null,"Login failed"));
	}

}
