package com.noduco.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.noduco.entity.CustomUser;
import com.noduco.reposatory.CustomUserRepo;
import com.noduco.reposatory.RoleRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private CustomUserRepo userRepository;
    
    @Autowired
    private RoleRepo rRepo;
    
    public String addUser(CustomUser cuser) {
    	rRepo.saveAll(cuser.getRoles());
    	userRepository.save(cuser);
    	return "User registered successfully";
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()))
                .build();
	
	}



}
