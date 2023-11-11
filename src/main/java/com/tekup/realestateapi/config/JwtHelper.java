package com.tekup.realestateapi.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

    //requirement :
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    //    public static final long JWT_TOKEN_VALIDITY =  60;
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
   
    /*public String getRolesFromToken(String token) {
        Object rolesClaim = getClaimFromToken(token, claims -> claims.get("roles", Object.class));

        if (rolesClaim instanceof List<?>) {
            List<?> rolesList = (List<?>) rolesClaim;
            return rolesList.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        } else if (rolesClaim != null) {
            return rolesClaim.toString();
        } else {
            return "";
        }
    }

    public String getRolesFromToken(String token) {
        Object rolesClaim = getClaimFromToken(token, claims -> claims.get("roles", Object.class));

        if (rolesClaim != null) {
            return String.valueOf(rolesClaim);
        } else {
            return "";
        }
    }

    public String getRolesFromToken(String token) {
        List<?> rolesClaim = getClaimFromToken(token, claims -> claims.get("roles", List.class));

        if (rolesClaim != null) {
            return rolesClaim.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        } else {
            return "";
        }
    }*/

    public String getRolesFromToken(String token) {
        List<?> rolesClaim = getClaimFromToken(token, claims -> claims.get("roles", List.class));

        if (rolesClaim != null) {
            List<String> roles = rolesClaim.stream()
                    .map(obj -> {
                        if (obj instanceof GrantedAuthority) {
                            return ((GrantedAuthority) obj).getAuthority();
                        } else if (obj instanceof Map) {
                            return ((Map<?, ?>) obj).get("authority").toString();
                        } else {
                            return obj.toString();
                        }
                    })
                    .collect(Collectors.toList());

            System.out.print("Roles from Token: " + roles);
            return String.join(",", roles);
        } else {
            return "";
        }
    }


    
    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
   /* public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("roles", roles);       
        return doGenerateToken(claims, userDetails.getUsername());
    }
*/
    /*public String generateToken(UserDetails userDetails) {
     /*   Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> roles = authorities.stream()
        		   .map(GrantedAuthority::getAuthority)
        		   .collect(Collectors.toList());
        
        claims.put("roles", roles);       
        return doGenerateToken(claims, userDetails.getUsername());
    	
    	   Map<String, Object> claims = new HashMap<>();
           claims.put("username", userDetails.getUsername());

           Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
           List<String> roles = authorities.stream()
                   .map(GrantedAuthority::getAuthority)
                   .collect(Collectors.toList());

           claims.put("roles", String.join(",", roles));
           return doGenerateToken(claims, userDetails.getUsername());
    }*/

    public String generateToken(UserDetails userDetails) {
      /*  Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());

        claims.put("roles", userDetails.getAuthorities()); 
        return doGenerateToken(claims, userDetails.getUsername());*/
    	        Map<String, Object> claims = new HashMap<>();
    	        claims.put("username", userDetails.getUsername());

    	        claims.put("roles", userDetails.getAuthorities()); 
    	        return doGenerateToken(claims, userDetails.getUsername());
    	    

    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
 // Modify the validateToken method
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final String roles = getRolesFromToken(token);

        System.out.println("Roles from Token: " + roles);
        //System.out.println("Roles from UserDetails: " + userDetails.getAuthorities());

        
        List<String> tokenRoles = Arrays.asList(roles.split(","));
        System.out.println("tokenRoles from : " + tokenRoles);

        return username.equals(userDetails.getUsername()) && userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .allMatch(tokenRoles::contains);
    }


}