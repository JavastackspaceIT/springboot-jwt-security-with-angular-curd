package com.javastackspaceit.security.config;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javastackspaceit.model.User;
import com.javastackspaceit.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userData = null;

		Optional<User> user = userRepository.findByUserName(username);

		if (user.isPresent()) {

			userData = user.get();
			System.out.println("userData  :" + userData);
		}
		return new org.springframework.security.core.userdetails.User(userData.getUserName(), userData.getPassword(),
				new ArrayList<>());
	}
}
