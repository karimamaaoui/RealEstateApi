package com.tekup.realestateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.Role;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.service.RoleService;
import com.tekup.realestateapi.service.UserService;

@RestController
@RequestMapping("/role")
public class RoleController {

	
	@Autowired
    private RoleService roleService;

    /**
     * add role
     */

    @PostMapping("/add")
    public String addRole(@RequestBody Role role) {
    	
        roleService.addRole(role);

        return "success add role";
    }
    
    /**
     * get roles as list
     */

    @GetMapping("/getall")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /**
     * get role by id
     */

    @GetMapping("/get/{id}")
    public Role getRoles(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteRoles(@PathVariable Long id){
    	roleService.deleteRole(id);

        return ResponseEntity.noContent().build();
    }


}
