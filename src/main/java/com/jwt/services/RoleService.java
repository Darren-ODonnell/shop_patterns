package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.RoleRepository;
import com.jwt.security.ERole;
import com.jwt.security.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // return all Roles

    public List<Role> list(){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) new MyMessageResponse("Error: No Roles listed", MessageTypes.WARN);
        return roles;
    }

    public List<Role> findAll(){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) new MyMessageResponse("Error: No Roles listed", MessageTypes.WARN);
        return roles;
    }

    // return Role by id

    public Role findByName( ERole name){
        Role role = roleRepository.findByName(name).orElse(new Role());
        if(role.getId()==null)
            new MyMessageResponse(String.format("Role name: %s not found", name), MessageTypes.ERROR);
        return role;
    }

    // edit/update a Role record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, Role role){

        // check if exists first
        // then update

        if(!roleRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        roleRepository.save(role);
        return ResponseEntity.ok(new MyMessageResponse("Role record updated", MessageTypes.INFO));
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
