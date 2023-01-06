package com.capstone.compensation.validation;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.capstone.compensation.entity.UserEntity;
import com.capstone.compensation.entity.UserRoleEntity;
import com.capstone.compensation.errorresponse.MessageResponse;
import com.capstone.compensation.model.SignUpRequest;
import com.capstone.compensation.model.UserRoles;
import com.capstone.compensation.repository.UserRepository;
import com.capstone.compensation.repository.UserRoleRepository;
import com.capstone.compensation.service.RegisterUserService;

@Component
public class UserValidation {

	@Autowired
	RegisterUserService registerUserService;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	public ResponseEntity<?> validateUser(SignUpRequest signUpRequest) {

		if (registerUserService.existsByEmailId(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		if (signUpRequest != null && signUpRequest.getRole() != null && !signUpRequest.getRole().isEmpty()) {
//				for (String s : signUpRequest.getRole()) {
			String s = signUpRequest.getRole();
			if (!s.equalsIgnoreCase("user") && !s.equalsIgnoreCase("reporter")
					&& !s.equalsIgnoreCase("compensationuser")) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Role is Not Valid!"));
			}
		}
		
		UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		String strRoles = signUpRequest.getRole();
		Set<UserRoleEntity> roles = new HashSet<>();

		if (strRoles == null) {
			UserRoleEntity userRole = userRoleRepository.findByName(UserRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
//						strRoles.forEach(role -> {
			switch (strRoles) {
			case "reporter":
				UserRoleEntity readerRole = userRoleRepository.findByName(UserRoles.ROLE_REPORTER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(readerRole);

				break;
			case "author":
				UserRoleEntity authorRole = userRoleRepository.findByName(UserRoles.ROLE_COMPENSATION)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(authorRole);

				break;
			default:
				UserRoleEntity userRole = userRoleRepository.findByName(UserRoles.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
//						});
		}
		user.setRoles(roles);		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
