package com.jwt.controllers;

import com.jwt.models.Firstname;
import com.jwt.models.FirstnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.FirstnameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@Controller
@RequestMapping("/firstname")
public class FirstnameController {
    private static final Logger logger = LoggerFactory.getLogger(FirstnameController.class);

    public final FirstnameService firstnameService;

    @Autowired
    public FirstnameController(FirstnameService firstnameService) {
        this.firstnameService = firstnameService;
    }

    // return all Firstnames - done

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    List<Firstname> list(){
        return firstnameService.list();
    }

    // delete by id

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
        return firstnameService.deleteById(id);
    }

    // return Firstname by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Firstname findById(@RequestParam("id") Long id){
        return firstnameService.findById( id);
    }

    // return Firstname by firstname

    @GetMapping(value="/findByFirstname/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public @ResponseBody Firstname findByFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        return firstnameService.findByFirstname(firstnameModel);
    }

    // return irish firstname given the english firstname

    @GetMapping(value="/findIrish/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Firstname findIrishFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        return firstnameService.findIrishFirstname( firstnameModel);
    }

//    // return english lastname given the irish lastname
//
//    @GetMapping(value="/findEnglish/")
//    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
//    public @ResponseBody List<Firstname> findEnglishFirstname(@ModelAttribute FirstnameModel firstnameModel) {
//        return firstnameService.findEnglishFirstname( firstnameModel);
//    }

    // add new firstname

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody  ResponseEntity<MessageResponse> add( @ModelAttribute FirstnameModel firstnameModel){
        return firstnameService.add( firstnameModel);
    }

    // edit/update a firstname record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> update(@RequestParam("id") Long id, @ModelAttribute FirstnameModel firstnameModel){
        return firstnameService.update(id, firstnameModel);
    }
}
