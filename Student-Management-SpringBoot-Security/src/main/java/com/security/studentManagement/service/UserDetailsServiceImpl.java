package com.security.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.studentManagement.entity.User;
import com.security.studentManagement.repository.UserRepository;
import com.security.studentManagement.security.MyUserDetails;



public class UserDetailsServiceImpl implements UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;
	     
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepository.getUserByUsername(username);
	         
		if (user == null) 
		{
			throw new UsernameNotFoundException("Could not find user");
		}
	         
		return new MyUserDetails(user);
	}

}
