package com.jwt.controllers;


import com.jwt.payload.request.ChangePasswordRequest;
import com.jwt.payload.request.LoginRequest;
import com.jwt.payload.request.SignupRequest;
import com.jwt.payload.response.JwtResponse;
import com.jwt.payload.response.MessageResponse;

import com.jwt.security.ERole;
import com.jwt.security.Role;
import com.jwt.security.User;
import com.jwt.security.jwt.JwtUtils;
import com.jwt.security.services.UserDetailsImpl;
import com.jwt.services.FellowshipService;
import com.jwt.services.RoleService;
import com.jwt.services.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    FellowshipService fellowshipService;
    UserService userService;
    RoleService roleService;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, FellowshipService fellowshipService, UserService userService, RoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.fellowshipService = fellowshipService;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    // login

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                fellowshipService.getByEmail(userDetails.getEmail()),
                roles));
    }

    // Register

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody  SignupRequest signupRequest) {
        if(!signupRequest.getPassword().equals(signupRequest.getPasswordConfirm())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords do not match!"));
        }

        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_PLAYER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleService.findByName(ERole.ROLE_COACH);
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_PLAYER);
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userService.add(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    // return all users

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<User> list(){
        return userService.findAll();
    }

    // return user by id

    @GetMapping(value="/user/findById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody User findById(@RequestParam("id") Long id){
        return userService.findById(id);

    }

    @GetMapping(value="/user/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<User> findAll(){  return userService.findAll();    }


    // return user by username

    @GetMapping(value="/user/findByUsername")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody User findByUsername(@RequestParam("username") String username) {
        return userService.findByUsername(username);

    }

    // delete by id

    @DeleteMapping(value="/user/deleteById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
        return userService.deleteById(id);
    }

    // edit/update a user record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> update(@ModelAttribute User user){
        return  userService.update( user);

    }

    @GetMapping(value="/checkToken" )
    public @ResponseBody boolean checkToken(@RequestParam("token") String token){
        return jwtUtils.validateJwtToken(token);
    }

    @PostMapping(value="/changePassword" )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest){

        // verify new password and passwordConfirm are the same
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getPasswordConfirm()))
            return ResponseEntity.ok("New Password and Confirm Password do not match");

        // check that a user exists and retrieve user
        User user = userService.findByUsername(changePasswordRequest.getUsername());
        if(user.getId()==null)
            return ResponseEntity.ok("User Details do not exist");

        // encode oldPassword and check with db
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

        boolean passwordOk = bc.matches(changePasswordRequest.getOldPassword(), user.getPassword());

        // verify that the oldPassword and the password in the db are the same

        if (!passwordOk) {
            return ResponseEntity.ok("Old Password does not match");
        }

        // set the new password and save to the db
        user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
        userService.update(user);

        return ResponseEntity.ok("Password changed successfully");
    }
}
