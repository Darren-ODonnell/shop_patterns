package com.jwt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.models.Lastname;
import com.jwt.models.LastnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.LastnameService;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.models.Lastname;
import com.jwt.models.LastnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.LastnameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@Log
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/lastname")
public class LastnameController {
    private static final Logger logger = LoggerFactory.getLogger(LastnameController.class);
    public final LastnameService lastnameService;

    @Autowired
    public LastnameController(LastnameService lastnameService) {
        this.lastnameService = lastnameService;
    }

    // return all lastnames

    @GetMapping(value={"/","/list"} )
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Lastname> list(){
        return lastnameService.list();
    }

    // return Lastname by id

    @GetMapping(value="/findById")
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody Lastname findById(@RequestParam("id") Long id){
        return lastnameService.findById(id);
    }

    // return irish lastname given the english lastname

    @GetMapping(value={"/findIrish","/findByLastname"})
        @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Lastname> findIrishLastname(@ModelAttribute LastnameModel lastnameModel) {
        return lastnameService.findByEnglishLastname(lastnameModel);
    }

    // return english lastname given the irish lastname

    @GetMapping(value="/findEnglish")
    @PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Lastname> findEnglishLastname(@ModelAttribute LastnameModel lastnameModel) {
        return lastnameService.findByIrishLastname(lastnameModel);
    }

    // delete lastname

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Lastname> delete(@RequestBody Lastname lastname){
        return lastnameService.delete(lastname);

    }

    // add record

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> add(@RequestBody LastnameModel data){
        return lastnameService.add(data);
    }



    // update record

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> update(@RequestBody Lastname lastname){
        return lastnameService.update(lastname.getId(), lastname);
    }

}
