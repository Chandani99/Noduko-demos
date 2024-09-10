package com.noduco.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfigs {
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		 http
		 .csrf(csrf -> csrf.disable())
	     .cors(cors -> cors.disable())
		 .authorizeHttpRequests(
				 (req) -> req.anyRequest().authenticated()
		          );
		 http.httpBasic(Customizer.withDefaults());
		 
//		 http.authorizeHttpRequests(
//		          (req) -> req.requestMatchers("/book/add-book","/book/delete-book/**").authenticated()
//		          );
//		 http.httpBasic(Customizer.withDefaults());
		 
		 return http.build();
		 
	}

}
