package com.tekup.realestateapi.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.config.EmailVerification.EmailSender;
import com.tekup.realestateapi.models.ERole;
import com.tekup.realestateapi.models.Role;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.repository.RoleRepository;
import com.tekup.realestateapi.repository.UserRepository;
import com.tekup.realestateapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
	@Autowired
	private EmailSender emailSender;
    /**
     * add user
     */
    @Override
    public void addUser(User user) {
       // Role defaultRole = roleRepository.findById(Long.valueOf(1)).orElse(null);
       // user.setRoles(defaultRole);
        userRepository.save(user);
    }

    /**
     * get users as list
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * get user by id
     */

    @Override
    public User getUser(Integer id) {
//        check weather the user is in database or not
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        return user;
    }

    @Override
    public void deleteUser(Integer id) {
//        check weather the user is in database or not
        User user = userRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        userRepository.delete(user);
    }

	public User findByEmail(String email) {
		  User user = userRepository
	                .findByEmail(email)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user email:" + email));

	        return user;
	}

	/*@Override
	public User createUser(User user) {
	   /*Role userRole = roleRepository.findByName(ERole.CLIENT)
	            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    
	    user.setRole_id(userRole);
	    
		
		  Set<Role> roles = new HashSet<>();
		    Role userRole = roleRepository.findByName(ERole.CLIENT)
		            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		        roles.add(userRole);
		user.setRoles(roles);
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    return userRepository.save(user);
	}

	@Override
	public User createUser(User user) {
	    Role userRole = roleRepository.findByName(ERole.CLIENT)
	            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    user.setRole(userRole); 
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    return userRepository.save(user);
	}
	
	@Override
	public User createUser(User user) {
	    try {
	        Role userRole = roleRepository.findByName(ERole.CLIENT)
	                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	        
	        user.setRoles(userRole);
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    } catch (RuntimeException e) {
	        e.printStackTrace(); 
	        throw new RuntimeException("Error creating user", e); 
	    }
	}
*/
	@Override
	public User createUser(User user) {
	    try {
	       /* Role userRole = roleRepository.findByName(ERole.CLIENT)
	                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	        
	        user.setRole_id(userRole);
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);*/
	    	
	    	
	  	  Set<Role> roles = new HashSet<>();
		    Role userRole = roleRepository.findByName(ERole.CLIENT)
		            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		        roles.add(userRole);
		user.setRoles(roles);
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user.setEnabled(false);
	    
	    User savedUser = userRepository.save(user);
        sendActivationEmail(savedUser);
        return savedUser;

	    } catch (RuntimeException e) {
	        e.printStackTrace(); 
	        throw new RuntimeException("Error creating user", e); 
	    }
	}

	  public void activateUser(String email) {
	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

	        // Set the user as enabled
	        user.setEnabled(true);
	        userRepository.save(user);
	    }

	    private void sendActivationEmail(User user) {
	        // Create the activation link using the user's email or any other unique identifier
	        String activationLink = "http://localhost:8080/auth/activate?email=" + user.getEmail();

	        // Build the email content
	        String emailContent = buildActivationEmail(user.getFirstname(), activationLink);

	        // Send the email
	        emailSender.send(user.getEmail(), emailContent);
	    }
	    
	    private String buildActivationEmail(String name, String link) {
	        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
	                "\n" +
	                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
	                "\n" +
	                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
	                "        \n" +
	                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
	                "          <tbody><tr>\n" +
	                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
	                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td style=\"padding-left:10px\">\n" +
	                "                  \n" +
	                "                    </td>\n" +
	                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
	                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
	                "                    </td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "              </a>\n" +
	                "            </td>\n" +
	                "          </tr>\n" +
	                "        </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
	                "      <td>\n" +
	                "        \n" +
	                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "\n" +
	                "\n" +
	                "\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
	                "        \n" +
	                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
	                "\n" +
	                "</div></div>";
	    }


}