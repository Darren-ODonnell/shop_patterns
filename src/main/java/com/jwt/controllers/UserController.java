package com.jwt.controllers;


import com.jwt.exceptions.NotFoundException;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.RoleRepository;
import com.jwt.repositories.UserRepository;
import com.jwt.security.ERole;
import com.jwt.security.Role;
import com.jwt.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    UserRepository userRepository;
    RoleRepository roleRepository;



    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Note: No User can be added from here - only via AuthController (users signup)

    // return all users - done

    @GetMapping(value={"/","/list"} )
    @PreAuthorize(" hasRole('ROLE_ADMIN')")
    public @ResponseBody
    List<User> list(Model model){
        return userRepository.findAll();
    }

    // delete by id

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){

        logger.info("delete User by id = "+id);
        if(userRepository.existsById(id))
            userRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete User with id: "+id));

        return ResponseEntity.ok(new MessageResponse("User deleted with id: " + id));
    }

    // add user - only used during testing - this only done in signup.

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public @ResponseBody  ResponseEntity<MessageResponse> add(HttpServletRequest request, Model model, @ModelAttribute User user){
    public @ResponseBody  ResponseEntity<MessageResponse> add(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password){

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new NotFoundException(String.format("Error: Role: %s is not found.",ERole.ROLE_USER)));

        roles.add(userRole);

        user.setRoles(roles);

        if(userRepository.existsByUsername(username))
           ResponseEntity.ok(new MessageResponse("Error: User already exists"));
        else
           userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("New User Added"));
    }

    // return user by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody User findById(Model model, @RequestParam("id") Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Error: User Id : %d is not found.",id)));
    }

    // return user by firstname

    @GetMapping(value="/findByUsername/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    Optional<User> findByUsername(Model model, @RequestParam("username") String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: Username : ["+username+"] is not found.")));
    }

    // edit/update a user record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> update(Model model, @ModelAttribute User user){
        Long id = user.getId();

        // check if exists first
        // then update

        if(userRepository.existsById(id))
            userRepository.save(user);
        else
            ResponseEntity.ok(new MessageResponse("Error: Id does not exist ["+id+"] -> cannot update record"));

        return ResponseEntity.ok(new MessageResponse("User record updated"));
    }


}
