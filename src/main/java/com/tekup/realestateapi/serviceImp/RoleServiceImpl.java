/*package com.tekup.realestateapi.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.Role;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.repository.RoleRepository;
import com.tekup.realestateapi.repository.UserRepository;
import com.tekup.realestateapi.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
    @Autowired
    private RoleRepository roleRepository;
    /**
     * add user
     
    @Override
    public void addRole(Role role) {
    	roleRepository.save(role);
    }
	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
        return roleRepository.findAll();
	}
	@Override
	public Role getRole(Long id) {
		   Role role = roleRepository
	                .findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid role Id:" + id));

	        return role;
	}
	@Override
	public void deleteRole(Long id) {
		 Role role = roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

		 roleRepository.delete(role);		
	}
	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

}*/
