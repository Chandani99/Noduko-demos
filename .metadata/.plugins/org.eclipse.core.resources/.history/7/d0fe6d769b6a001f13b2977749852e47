package com.noduco.securityConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomEndPointsSecurity {

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		
		 http.authorizeHttpRequests(
		          (req) -> req.antMatchers("/book/add-book","/book/delete-book/**").authenticated()
		          );
		 http.httpBasic(Customizer.withDefaults());
		 
		          
		 return http.build();
		
	}
}
