package com.noduco.securityConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class DbRollBasedSecurityConfig {

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
		.authorizeHttpRequests((auth) -> auth.requestMatchers("/admin/home", "/admin/office").hasAuthority("admin")
				.requestMatchers("/users/delete/{username}", "/users/update", "/users/all").hasAuthority("user")
				.requestMatchers("users/register", "/global/home", "/global/office").permitAll());
		http.httpBasic(Customizer.withDefaults());

		return http.build();
		
	}
	@Bean
	public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        // Create users with roles
        UserDetails admin = User
            .withUsername("admin")
            .password("{noop}adminpass")
            .roles("ADMIN")
            .build();
        
            UserDetails user = User
            .withUsername("user")
            .password("{noop}userpass")
            .roles("USER")
            .build();
        
        return new InMemoryUserDetailsManager(admin, user);
        
    }
	
	public PasswordEncoder encodePass() {
		System.out.println("encodePass : ");
		return new BCryptPasswordEncoder();
	}
	
}
