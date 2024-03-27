package com.nnk.springboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired 
	private UserRepository userRepository;

	/*
	 * load user by username in bdd 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		return new org.springframework.security.core.userdetails.User (user.getUsername(), user.getPassword(), getGrantedAuthority(user.getRole()));
	}

	/*
	 * add authority based on role 
	 */
	private List<GrantedAuthority> getGrantedAuthority(String role){
		List<GrantedAuthority> authority= new ArrayList<GrantedAuthority>();
		authority.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authority;
	}

}
