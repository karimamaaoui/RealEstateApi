package com.tekup.realestateapi.service;

import java.util.List;

import com.tekup.realestateapi.models.Role;


public interface RoleService {

	 	void addRole(Role role);
	    List<Role> getRoles();
	    Role getRole(Long  id);
	    void deleteRole(Long  id);
	    
	    Role createRole(Role role);

}

