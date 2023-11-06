package com.tekup.realestateapi.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
	
	private String jwtToken;
	private String username;
	private List<String> roles;
}
