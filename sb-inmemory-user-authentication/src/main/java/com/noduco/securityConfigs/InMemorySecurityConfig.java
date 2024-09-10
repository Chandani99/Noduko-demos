package com.noduco.securityConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class InMemorySecurityConfig {

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		
		 http
		 .csrf(csrf -> csrf.disable())
	     .cors(cors -> cors.disable())
		 .authorizeHttpRequests(
        (req) -> req.requestMatchers("/book/admin/**").hasRole("ADMIN")
        .requestMatchers("/book/user/**").hasRole("USER").anyRequest().authenticated()
        );
		 
		          
		 return http.build();
		
	}
	@Bean
	public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        // Create users with roles
        UserDetails admin = User
            .withUsername("admin")
            .password("adminpass")
            .roles("ADMIN")
            .build();
        
            UserDetails user = User
            .withUsername("user")
            .password("userpass")
            .roles("USER")
            .build();
        
        return new InMemoryUserDetailsManager(admin, user);
        
    }
	
}
