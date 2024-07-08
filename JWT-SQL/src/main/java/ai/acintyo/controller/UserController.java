package ai.acintyo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.acintyo.model.User;
import ai.acintyo.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	/*
	 * In this code, we retrieve the authenticated user from the security
	 * context that has been set in the file JwtAuthenticationFilter.java at line
	 * 61.
	 * 
	 * we can see the UserService is injected in the controller and provides the
	 * function allUsers() that retrieves the list of users in the database and
	 * returns it.
	 */

	private final UserService userService;
	
	@GetMapping("/me")
	public ResponseEntity<User> authenticatedUser(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User currentUser = (User) authentication.getPrincipal();
		
		return ResponseEntity.ok(currentUser);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> allUser(){
		
		List<User> allUsers = userService.getAllUsers();
		
		return ResponseEntity.ok(allUsers);
	}
}
