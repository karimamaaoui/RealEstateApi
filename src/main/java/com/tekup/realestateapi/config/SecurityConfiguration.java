package com.tekup.realestateapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private JwtAuthentication jwtAuthentication;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//configuration
		
      
		http.csrf(csrf -> csrf.disable())
        .cors(cors->cors.disable())
        .authorizeHttpRequests(
        		auth->auth
        		.requestMatchers("auth/**").permitAll()
        		.requestMatchers("/country/**").permitAll()
                .requestMatchers("/town/**").hasAnyRole("ADMIN", "CLIENT")
                .requestMatchers("/realestate/**").hasAnyRole("ADMIN", "CLIENT")

                .requestMatchers("/home/**").hasRole("ADMIN")
        		.requestMatchers("/category/list").hasAnyRole("ADMIN","CLIENT")
        		.requestMatchers("/category/get").hasAnyRole("ADMIN","CLIENT")
        		.requestMatchers("/category/**").hasRole("ADMIN") 
        		.requestMatchers("/users/**").hasRole("ADMIN") 
        		
        		
        		
        		
        		
        		.anyRequest().authenticated())
        		.exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthentication))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                		
                		);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
}
