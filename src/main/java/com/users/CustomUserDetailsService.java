package com.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository userRepo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user= userRepo.findByUsername(username);
		 if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	            
	        }
	        return new CustomUserDetails (user);
	   
	}

}
