package org.james.springsecurity.jwt.controller;

import org.james.springsecurity.jwt.model.JwtRequest;
import org.james.springsecurity.jwt.model.JwtResponse;
import org.james.springsecurity.jwt.service.UserService;
import org.james.springsecurity.jwt.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@Autowired
	JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String homePage() {
		return "<h1>User home page</h1>";
	}
	
//	@GetMapping("/admin")
//	public String admin() {
//		return "<h1>Administration time</h1>";
//	}
//	
	@PostMapping("/authenticate")
//	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				jwtRequest.getUsername(),
				jwtRequest.getPassword()));
//		} catch (BadCredentialsException e) {
		} catch (AuthenticationException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		// passed authentication
		final UserDetails userDetails = 
				userService.loadUserByUsername(jwtRequest.getUsername());
		
		final String token = jwtUtility.generateToken(userDetails);
		
//		return new JwtResponse(token);
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
