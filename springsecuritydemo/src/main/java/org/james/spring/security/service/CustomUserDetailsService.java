package org.james.spring.security.service;

import org.james.spring.security.model.CustomUserDetails;
import org.james.spring.security.model.User;
import org.james.spring.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {


	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findByUserId(username);
		
		if (user == null) 
			throw new UsernameNotFoundException("User not found");

		return new CustomUserDetails(user);
	}

}
