package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.UserRepository;
import com.jwt.security.User;
import com.jwt.security.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // return all Users

    public List<User> list(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) new MyMessageResponse("Error: No Users listed", MessageTypes.WARN);
        return users;
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) new MyMessageResponse("Error: No Users listed", MessageTypes.WARN);
        return users;
    }


    public User findByUsername(String username){
        User user = userRepository.findByUsername(username).orElse(new User());
        if (!user.getUsername().equals(username))
            new MyMessageResponse(String.format("User name: %d not found", username), MessageTypes.ERROR);
        return user;
    }


    // return User by id

    public User findById( Long id){
        User user = userRepository.findById(id).orElse(new User());
        if(user.getId()==null)
            new MyMessageResponse(String.format("User id: %d not found", id), MessageTypes.ERROR);
        return user;
    }

    // check if a username exists

    public Boolean existsByUsername( String username){
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail( String email){
        return userRepository.existsByEmail(email);
    }
    public boolean existsById( Long id){
        return userRepository.existsById(id);
    }

    // check if a email exists

    public ResponseEntity<MessageResponse> add(User user){

        if(!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
            return ResponseEntity.ok(new MyMessageResponse("new User added", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Username already exists", MessageTypes.WARN));
        }

    }

    // delete by id

    public ResponseEntity<MessageResponse> delete( User user){
        Long id = user.getId();
        if(!userRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete User with id: "+id, MessageTypes.WARN));

        userRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("User deleted with id: " + id, MessageTypes.INFO));
    }
    public ResponseEntity<MessageResponse> deleteById( Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok(new MyMessageResponse("User deleted with id: " + id, MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete User with id: " + id, MessageTypes.WARN));
        }

    }

    // edit/update a User record - only if record with id exists

    public ResponseEntity<MessageResponse> update(User user){

        // check if exists first
        // then update

        if(userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return ResponseEntity.ok(new MyMessageResponse("User record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist [" + user.getId() + "] -> cannot update record", MessageTypes.WARN));
        }

    }



}
